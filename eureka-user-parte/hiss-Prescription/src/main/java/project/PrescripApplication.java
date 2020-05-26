package project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.project.mapper")
public class PrescripApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrescripApplication.class,args);
    }
}
