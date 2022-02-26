package top.bluer.result.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.bluer.result.annotation.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @program: bluer-tool
 * @description: 定义返回结果拦截器
 * @author: bluer
 * @date: 2022-02-24 18:58
 * codes: 扁鹊
 **/
@Component
public class BluerResponseResultInterceptor implements HandlerInterceptor {
    /**
     * 使用统一返回体的标识
     */
    private static final String RESPONSE_RESULT_ANNOTATION = "BLUER-RESPONSE-RESULT-ANNOTATION";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 正在处理请求的方法Bean
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取当前类
            final Class<?> clazz = handlerMethod.getBeanType();
            // 获取当前方法
            final Method method = handlerMethod.getMethod();
            // 判断是否在类对象上加了注解
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                // 设置该请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANNOTATION, clazz.getAnnotation(ResponseResult.class));
            }
            // 判断是否在方法上加了注解
            else if (method.isAnnotationPresent(ResponseResult.class)) {
                // 设置该请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANNOTATION, method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
