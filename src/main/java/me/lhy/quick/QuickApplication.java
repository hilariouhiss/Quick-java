package me.lhy.quick;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("me.lhy.quick.mapper")
public class QuickApplication {

    static void main(String[] args) {
        SpringApplication.run(QuickApplication.class, args);
    }

}
