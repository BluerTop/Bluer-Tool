package top.bluer.fis;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: Bluer-Tool
 * @description: 文件上传/删除
 * @author: bluer
 * @date: 2022-02-18 12:41
 * codes: 扁鹊
 **/
public interface FisTemplate {
    /**
     * @description: 上传文件
     * @date: 2022/2/18 12:42
     * @codes: 扁鹊
     **/
    String putFile(MultipartFile file);

    /**
     * @description: 删除文件
     * @date: 2022/2/18 12:42
     * @codes: 扁鹊
     **/
    boolean delFile(String path);
}
