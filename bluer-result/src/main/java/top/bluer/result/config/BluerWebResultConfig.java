package top.bluer.result.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.bluer.result.interceptor.BluerResponseResultInterceptor;

import java.util.List;

/**
 * @program: bluer-tool
 * @description: 在Web拦截器加入自定义拦截器
 * @author: bluer
 * @date: 2022-02-24 19:00
 * codes: 扁鹊
 **/
@Configuration
public class BluerWebResultConfig extends WebMvcConfigurerAdapter {
    /**
     * @description: 添加自定义拦截器
     * @date: 2022-02-24 19:00
     * @codes: 扁鹊
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BluerResponseResultInterceptor()).addPathPatterns("/**");
    }

    /**
     * @description: 解决直接返回String报错的bug
     * @date: 2022/2/24 19:51
     * @codes: 扁鹊
     * 因为在所有的 HttpMessageConverter 实例集合中，
     * StringHttpMessageConverter 要比其它的 Converter 排得靠前一些。
     * 需要将处理 Object 类型的 HttpMessageConverter 放得靠前一些，
     * 这可以在 Configuration 类中完成
     **/
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }
}
