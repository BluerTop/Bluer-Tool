package top.bluer.result.annotation;

import java.lang.annotation.*;

/**
 * @program: bluer-tool
 * @description: 包装返回结果的自定义注解
 * @date: 2021-10-15 15:51
 * codes: 扁鹊
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
