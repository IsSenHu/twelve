package com.husen.twelvemybatis.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestBean {
    private String username;
    private String password;

    public void out() {
        System.out.println(username + ":" + password);
    }
}
