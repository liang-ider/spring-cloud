package cn.controller;

import cn.entity.User;
import cn.servers.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginServer loginServer;
    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user.getUser_code()+":"+user.getPassword()+"===================");
        if (loginServer.loginUser(user)){
            return "登录成功";
        }
        return "登录失败";
    }
}
