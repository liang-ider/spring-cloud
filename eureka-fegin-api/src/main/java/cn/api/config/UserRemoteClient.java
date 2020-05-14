package cn.api.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-client")
public interface UserRemoteClient {
    @GetMapping("/hello")
    public String hello();
}
