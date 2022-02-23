package top.bluer.files.impl;

import io.minio.MinioClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import top.bluer.files.FisTemplate;
import top.bluer.files.config.FisProperties;
import top.bluer.files.utils.FileUtil;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * @program: bluer-tool
 * @description: 文件上传/删除 Minio
 * @author: bluer
 * @date: 2022-02-23 18:03
 * codes: 扁鹊
 **/
@Slf4j
public class FisMinioOssTemplateImpl implements FisTemplate {
    private final FisProperties fisProperties;
    private final MinioClient ossClient;

    @SneakyThrows
    public FisMinioOssTemplateImpl(FisProperties fisProperties) {
        this.fisProperties = fisProperties;
        ArrayList<String> minioOss = fisProperties.getOss();
        if (minioOss.size() != 4) throw new RuntimeException("Fis：MinioOss配置错误，请参考文档正确配置");
        String minioUrl = minioOss.get(0);
        this.ossClient = new MinioClient(minioUrl, minioOss.get(1), minioOss.get(2));
        String bucket = minioOss.get(3);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if (!StringUtils.hasText(bucket)) {
            bucket = uuid;
        }
        boolean bucketExists = ossClient.bucketExists(bucket);
        if (!bucketExists) {
            ossClient.makeBucket(bucket);
            log.info("创建 bucket: {} 成功...", bucket);
        }
        String finalBucket = bucket;
        fisProperties.setOss(new ArrayList<String>() {{
            add(minioUrl);
            add(minioOss.get(1));
            add(minioOss.get(2));
            add(finalBucket);
        }});
    }

    @SneakyThrows
    @Override
    public String putFile(MultipartFile file) {
        InputStream stream = file.getInputStream();
        String orgName = file.getOriginalFilename();
        if (!StringUtils.hasText(orgName)) throw new RuntimeException("Fis：获取文件名失败...");
        orgName = FileUtil.getFileName(orgName);
        String fileName = fisProperties.getPath() + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + '/' + System.currentTimeMillis() + '/' + orgName;
        ArrayList<String> minioOss = fisProperties.getOss();
        ossClient.putObject(minioOss.get(3), fileName, stream, stream.available(), "application/octet-stream");
        stream.close();
        return minioOss.get(0) + minioOss.get(3) + "/" + fileName;
    }

    @SneakyThrows
    @Override
    public boolean delFile(String path) {
        ArrayList<String> minioOss = fisProperties.getOss();
        path = path.replaceAll(minioOss.get(0) + minioOss.get(3) + "/", "");
        ossClient.removeObject(minioOss.get(3), path);
        return true;
    }
}
