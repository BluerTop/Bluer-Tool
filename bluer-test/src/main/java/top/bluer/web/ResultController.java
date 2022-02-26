package top.bluer.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.bluer.result.annotation.ResponseResult;

import java.util.ArrayList;

/**
 * @program: bluer-tool
 * @description: result测试
 * @author: bluer
 * @date: 2022-02-24 19:22
 * codes: 扁鹊
 **/

@RestController
@RequestMapping("/result")
public class ResultController {
    @ResponseResult
    @GetMapping("object")
    public ArrayList<String> object() {
        return new ArrayList<String>() {{
            add("result");
            add("object");
        }};
    }

    @GetMapping("string")
    public String string(@RequestParam("name") String name) {
        return name;
    }
}
