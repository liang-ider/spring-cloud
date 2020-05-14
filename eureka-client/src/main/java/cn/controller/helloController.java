package cn.controller;

import cn.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello 8086";
    }
    @PostMapping("/callUser")
    public User callUser(@RequestBody User user){
        return user;
    }
}
