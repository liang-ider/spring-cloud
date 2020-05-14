package cn.config;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    public enum Level{
        NONE,
        BASIC,
        HEADERS,
        FULL
    }
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        System.out.println("登录==================");
        return new BasicAuthRequestInterceptor("admin","1234567");
    }
}
