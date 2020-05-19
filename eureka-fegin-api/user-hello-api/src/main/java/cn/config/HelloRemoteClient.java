package cn.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-hello")
public interface HelloRemoteClient {
    @GetMapping("/hello/{id}")
    public String hello(@PathVariable String id);
}
