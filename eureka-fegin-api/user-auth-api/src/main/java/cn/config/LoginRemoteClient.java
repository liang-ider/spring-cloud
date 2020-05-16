package cn.config;

import cn.entity.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-auth",
        fallbackFactory = LoginRemoteClientFallbackFactory.class)
public interface LoginRemoteClient {
    @PostMapping("/login")
    String login(@RequestBody UserDTO user);
}
