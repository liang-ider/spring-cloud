package cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoClient8088 {
    public static void main(String[] args) {
        SpringApplication.run(DemoClient8088.class,args);
    }
}
