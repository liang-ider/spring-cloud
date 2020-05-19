package cn.controller;

import cn.config.HelloRemoteClient;
import cn.config.LoginRemoteClient;
import cn.entity.UserDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    LoginRemoteClient loginRemoteClient;
    @Autowired
    HelloRemoteClient helloRemoteClient;
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
    @GetMapping("/hello/{id}")
    public Object hello(@PathVariable String id){
        return helloRemoteClient.hello(id);
    }
}
