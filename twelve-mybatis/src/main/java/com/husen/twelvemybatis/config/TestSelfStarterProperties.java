package com.husen.twelvemybatis.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "test.self")
public class TestSelfStarterProperties {
    private String username;
    private String password;
}
