package cn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController1 {
    @GetMapping("/hello/{id}")
    public String hello(@PathVariable String id){
        return "hello1======"+id;
    }
}
