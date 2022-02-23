package top.bluer.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.bluer.files.FisTemplate;

import javax.annotation.Resource;

/**
 * @program: Bluer-Tool
 * @description:
 * @author: bluer
 * @date: 2022-02-18 13:43
 * codes: 扁鹊
 **/
@RestController
@RequestMapping("/fis")
public class FisController {

    @Resource
    private FisTemplate fisTemplate;

    @PostMapping("/putFile")
    public String putFile(MultipartFile file) {
        return fisTemplate.putFile(file);
    }

    @DeleteMapping("/delFile")
    public boolean delFile(@RequestParam("path") String path) {
        return fisTemplate.delFile(path);
    }
}
