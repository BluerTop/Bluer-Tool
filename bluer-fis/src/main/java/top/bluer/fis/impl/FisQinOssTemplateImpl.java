package top.bluer.fis.impl;

import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import top.bluer.fis.FisTemplate;
import top.bluer.fis.config.FisProperties;
import top.bluer.fis.utils.FileUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @program: bluer-tool
 * @description: 文件上传/删除 七牛云
 * @author: bluer
 * @date: 2022-02-23 19:04
 * codes: 扁鹊
 **/
public class FisQinOssTemplateImpl implements FisTemplate {
    private final FisProperties fisProperties;
    private final UploadManager uploadManager;
    private final BucketManager bucketManager;
    private final String upToken;

    public FisQinOssTemplateImpl(FisProperties fisProperties) {
        this.fisProperties = fisProperties;
        ArrayList<String> qinOss = fisProperties.getOss();
        if (qinOss.size() != 4) throw new RuntimeException("Fis：七牛云oss配置错误，请参考文档正确配置");
        Configuration cfg = new Configuration(Region.region0());
        uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qinOss.get(1), qinOss.get(2));
        bucketManager = new BucketManager(auth, cfg);
        upToken = auth.uploadToken(qinOss.get(3));
    }

    @SneakyThrows
    @Override
    public String putFile(MultipartFile file) {
        String orgName = file.getOriginalFilename();
        if (!StringUtils.hasText(orgName)) throw new RuntimeException("Fis：获取文件名失败...");
        orgName = FileUtil.getFileName(orgName);
        String fileName = fisProperties.getPath() + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + '/' + System.currentTimeMillis() + '/' + orgName;
        uploadManager.put(file.getInputStream(), fileName, upToken, null, null);
        ArrayList<String> qinOss = fisProperties.getOss();
        return qinOss.get(0) + fileName;
    }

    @SneakyThrows
    @Override
    public boolean delFile(String path) {
        ArrayList<String> qinOss = fisProperties.getOss();
        path = path.replaceAll(qinOss.get(0), "");
        bucketManager.delete(qinOss.get(3), path);
        return true;
    }
}
