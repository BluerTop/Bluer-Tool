package top.bluer.fis.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import top.bluer.fis.FisTemplate;

/**
 * @program: Bluer-Tool
 * @description: 文件上传/删除 未开启
 * @author: bluer
 * @date: 2022-02-18 12:43
 * codes: 扁鹊
 **/
@Slf4j
public class FisFalseTemplateImpl implements FisTemplate {

    @Override
    public String putFile(MultipartFile file) {
        log.error("Fis未开启 请修改配置文件【fis.deploy：true】开启Fis");
        return null;
    }

    @Override
    public boolean delFile(String path) {
        log.error("Fis未开启 请修改配置文件【fis.deploy：true】开启Fis");
        return false;
    }
}
