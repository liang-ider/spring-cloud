package cn.controller;

import cn.api.config.UserRemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRemoteClient userRemoteClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("/callHello")
    public String callHello(){
        return userRemoteClient.hello();
//                restTemplate.getForObject("http://eureka-client8088/hello",String.class);
    }
    @GetMapping("/choose")
    public Object chooseUrl(){
        return loadBalancerClient.choose("eureka-client8088");
    }
}
