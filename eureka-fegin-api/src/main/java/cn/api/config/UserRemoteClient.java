package cn.api.config;

import cn.api.entity.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "eureka-client")
public interface UserRemoteClient {
    @GetMapping("/hello")
    public String hello();
    @GetMapping("/hello2")
    public String hello2();
    @PostMapping("/callUser")
    public UserDTO callUser(@RequestBody UserDTO userDTO);
}
