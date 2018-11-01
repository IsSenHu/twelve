package com.husen.twelvemessagecenter.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by HuSen on 2018/11/1 21:31.
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String className, Class<T> tClass) throws ClassNotFoundException {
        Class<?> aClass = Class.forName(className);
        return applicationContext.getBean((Class<T>) aClass);
    }
}
