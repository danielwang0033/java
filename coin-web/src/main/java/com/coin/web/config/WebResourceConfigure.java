package com.coin.web.config;

import com.coin.web.interceptor.RequestInterceptor;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebResourceConfigure extends WebMvcConfigurationSupport {

    @Value("${io.url}")
    private String ioUrl;
    @Value("${io.accessKey}")
    private String accessKey;
    @Value("${io.secretKey}")
    private String secretKey;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        List<String> methods = new ArrayList<>();
        methods.add("OPTIONS");
        methods.add("HEAD");
        methods.add("GET");
        methods.add("POST");
        config.setAllowedMethods(methods);
        config.setMaxAge(3600L);
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(configSource));
        bean.setOrder(0);
        return bean;
    }

    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new RequestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getRequestInterceptor())
                .addPathPatterns("/**") //拦截所有再添加例外
                .excludePathPatterns(
                        "/error"
                );
        registry.addInterceptor(new RequestInterceptor());
    }

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(ioUrl)
                .credentials(accessKey, secretKey)
                .build();
    }
}
