package cn.controller;

import cn.config.LoginRemoteClient;
import cn.entity.UserDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    LoginRemoteClient loginRemoteClient;
    @PostMapping("/login")
    @HystrixCommand(fallbackMethod = "defaultCallHello")
    public String login(@RequestBody UserDTO user){
        System.out.println(user.getUser_code()+":"+user.getPassword()+"===================");
        return loginRemoteClient.login(user);
    }
    @GetMapping("/choose")
    public Object chooseUrl(){
        return loadBalancerClient.choose("eureka-client");
    }
}
