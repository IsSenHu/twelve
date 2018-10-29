package com.husen.twelvemybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.husen.twelvemybatis.dao.mapper")
public class TwelveMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwelveMybatisApplication.class, args);
    }
}
