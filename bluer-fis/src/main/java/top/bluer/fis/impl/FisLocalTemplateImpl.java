package top.bluer.fis.impl;

import lombok.SneakyThrows;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import top.bluer.fis.FisTemplate;
import top.bluer.fis.config.FisProperties;
import top.bluer.fis.utils.FileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: Bluer-Tool
 * @description: 文件上传/删除 本地
 * @author: bluer
 * @date: 2022-02-18 12:43
 * codes: 扁鹊
 **/
public class FisLocalTemplateImpl implements FisTemplate {
    private final FisProperties fisProperties;

    public FisLocalTemplateImpl(FisProperties fisProperties) {
        this.fisProperties = fisProperties;
    }

    /**
     * @description: 上传文件
     * @date: 2022/2/18 14:24
     * @codes: 扁鹊
     **/
    @SneakyThrows
    @Override
    public String putFile(MultipartFile multipartFile) {
        long timeMillis = System.currentTimeMillis();
        String bizPath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File file = new File(fisProperties.getPath() + File.separator + bizPath + File.separator + timeMillis + File.separator);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (!mkdirs) throw new RuntimeException("Fis：创建文件根目录失败...");
        }
        String filename = multipartFile.getOriginalFilename();
        if (!StringUtils.hasText(filename)) throw new RuntimeException("Fis：获取文件名失败...");
        String fileName = FileUtil.getFileName(filename);
        fileName = fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf(".")) + fileName.substring(fileName.indexOf(".")) : fileName;
        String savePath = file.getPath() + File.separator + fileName;
        File saveFile = new File(savePath);
        FileCopyUtils.copy(multipartFile.getBytes(), saveFile);
        String dbpath = File.separator + bizPath + File.separator + timeMillis + File.separator + fileName;
        if (dbpath.contains("\\")) {
            dbpath = dbpath.replace("\\", "/");
        }
        return dbpath;
    }

    /**
     * @description: 删除文件
     * @date: 2022/2/18 14:39
     * @codes: 扁鹊
     **/
    @Override
    public boolean delFile(String path) {
        File file = new File(fisProperties.getPath() + path);
        if (!file.exists()) return false;
        if (!file.isFile() && !file.exists()) return false;
        return file.delete();
    }
}
