package top.bluer.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.bluer.files.FisTemplate;

import javax.annotation.Resource;

/**
 * @program: Bluer-Tool
 * @description: fis测试
 * @author: bluer
 * @date: 2022-02-18 13:43
 * codes: 扁鹊
 **/
@RestController
@RequestMapping("/fis")
public class FisController {

    @Resource
    private FisTemplate fisTemplate;

    /**
     * @description: 上传文件
     * @date: 2022/2/24 12:57
     * @codes: 扁鹊
     * 参数：file 文件
     * 返回值：String 文件访问路径
     **/
    @PostMapping("/putFile")
    public String putFile(MultipartFile file) {
        return fisTemplate.putFile(file);
    }

    /**
     * @description: 删除文件
     * @date: 2022/2/24 12:56
     * @codes: 扁鹊
     * 参数：path 文件路径
     * 返回值：boolean 删除结果
     **/
    @DeleteMapping("/delFile")
    public boolean delFile(@RequestParam("path") String path) {
        return fisTemplate.delFile(path);
    }
}
