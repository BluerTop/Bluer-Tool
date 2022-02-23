package top.bluer.files.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import top.bluer.files.FisTemplate;
import top.bluer.files.impl.*;

import javax.annotation.Resource;

/**
 * @program: Bluer-Tool
 * @description: Files 自动配置
 * @author: bluer
 * @date: 2022-02-18 11:47
 * codes: 扁鹊
 **/
@Configuration
@EnableConfigurationProperties(FisProperties.class)
public class FisConfiguration {
    @Resource
    private FisProperties fisProperties;

    @Bean
    @ConditionalOnMissingBean(FisTemplate.class)
    public FisTemplate fisTemplate() {
        boolean deploy = fisProperties.isDeploy();
        if (!deploy) return new FisFalseTemplateImpl();
        String type = fisProperties.getType();
        if (!StringUtils.hasText(type)) return new FisFalseTemplateImpl();
        return type.equals("local") ? new FisLocalTemplateImpl(fisProperties) :
                type.equals("ali-oss") ? new FisAliOssTemplateImpl(fisProperties) :
                        type.equals("minio-oss") ? new FisMinioOssTemplateImpl(fisProperties) :
                                type.equals("qin-oss") ? new FisQinOssTemplateImpl(fisProperties) :
                                        new FisFalseTemplateImpl();
    }
}
