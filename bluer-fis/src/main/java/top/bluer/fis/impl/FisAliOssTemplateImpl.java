package top.bluer.fis.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import top.bluer.fis.FisTemplate;
import top.bluer.fis.config.FisProperties;
import top.bluer.fis.utils.FileUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * @program: Bluer-Tool
 * @description: 文件上传/删除 阿里云
 * @author: bluer
 * @date: 2022-02-18 12:43
 * codes: 扁鹊
 **/
@Slf4j
public class FisAliOssTemplateImpl implements FisTemplate {
    private final FisProperties fisProperties;
    private final OSS ossClient;

    public FisAliOssTemplateImpl(FisProperties fisProperties) {
        this.fisProperties = fisProperties;
        ArrayList<String> aliOss = fisProperties.getOss();
        if (aliOss.size() != 4) throw new RuntimeException("Fis：阿里云oss配置错误，请参考文档正确配置");
        try {
            this.ossClient = new OSSClientBuilder().build(aliOss.get(2), aliOss.get(0), aliOss.get(1));
        } catch (Exception e) {
            throw new RuntimeException("Fis：阿里云oss配置错误，请参考文档正确配置");
        }
        String bucket = aliOss.get(3);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if (!StringUtils.hasText(bucket)) {
            bucket = uuid;
        }
        boolean exists = ossClient.doesBucketExist(bucket);
        if (!exists) {
            ossClient.createBucket(bucket);
            log.info("创建 bucket: {} 成功...", bucket);
            String finalBucket = bucket;
            fisProperties.setOss(new ArrayList<String>() {{
                add(aliOss.get(0));
                add(aliOss.get(1));
                add(aliOss.get(2));
                add(finalBucket);
            }});
        }
    }

    @SneakyThrows
    @Override
    public String putFile(MultipartFile multipartFile) {
        String orgName = multipartFile.getOriginalFilename();
        if (!StringUtils.hasText(orgName)) throw new RuntimeException("Fis：获取文件名失败...");
        orgName = FileUtil.getFileName(orgName);
        String fileName = fisProperties.getPath() + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + '/' + System.currentTimeMillis() + '/' + orgName;
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentDisposition(fileName);
        meta.setContentLength(multipartFile.getSize());
        ArrayList<String> aliOss = fisProperties.getOss();
        ossClient.putObject(aliOss.get(3), fileName, multipartFile.getInputStream(), meta);
        ossClient.setObjectAcl(aliOss.get(3), fileName, CannedAccessControlList.PublicRead);
        return "https://" + aliOss.get(3) + "." + aliOss.get(2) + "/" + fileName;
    }

    @Override
    @SneakyThrows
    public boolean delFile(String path) {
        ArrayList<String> aliOss = fisProperties.getOss();
        path = path.replaceAll("https://" + aliOss.get(3) + "\\." + aliOss.get(2) + "/", "");
        ossClient.deleteObject(aliOss.get(3), path);
        return true;
    }
}
