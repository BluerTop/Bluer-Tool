# Bluer-Result

## 说明

- 全局接口返回封装
- 全局异常返回封装

## Maven 依赖

~~~xml

<dependency>
    <groupId>top.bluer</groupId>
    <artifactId>bluer-result</artifactId>
    <version>0.0.1</version>
</dependency>
~~~

## 使用

引入依赖即可使用

异常信息处理默认是全局的，正常信息返回需要在类或方法上加上 `@ResponseResult` 注解

- 代码示例

~~~java
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
@ResponseResult
@RestController
@RequestMapping("/result")
public class ResultController {

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
~~~

## 关闭

删除依赖即可关闭
