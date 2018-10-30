package com.husen.twelverabbitmq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by HuSen on 2018/10/30 16:08.
 */
@Component
@Slf4j
public class Receiver {
    @RabbitListener(queues = "base")
    public void onMessage(String message) {
        log.info("消费消息:{}", message);
    }
}
