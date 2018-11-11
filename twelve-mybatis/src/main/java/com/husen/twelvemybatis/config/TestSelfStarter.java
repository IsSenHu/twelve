package com.husen.twelvemybatis.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HuSen create on 2018-11-11 15:40:20
 */
public class TestSelfStarter {

    @Configuration
    @EnableConfigurationProperties(TestSelfStarterProperties.class)
    protected static class TestBeanConfiguration {
        private final TestSelfStarterProperties properties;

        public TestBeanConfiguration(TestSelfStarterProperties properties) {
            this.properties = properties;
        }

        @Bean
        public TestBean testBean() {
            TestBean testBean = new TestBean();
            testBean.setUsername(properties.getUsername());
            testBean.setPassword(properties.getPassword());
            return testBean;
        }
    }
}
