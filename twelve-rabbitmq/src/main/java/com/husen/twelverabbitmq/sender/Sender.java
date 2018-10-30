package com.husen.twelverabbitmq.sender;

import com.husen.utils.http.HttpConnectionUtils;
import com.husen.vo.common.CommonMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * Created by HuSen on 2018/10/30 14:25.
 */
@Component
@Slf4j
public class Sender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    private final RabbitTemplate rabbitTemplate;
    private String key = "key";
    @Autowired
    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack) {
            log.info("消息发送成功，准备告知生产者消息已成功发送!");
            // 已受理
            try {
                HttpConnectionUtils.doGet("http://localhost:8080/ackMessage?id=" + correlationData.getId());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            log.info("消息发送失败:{},{}", correlationData.getId(), cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息发送失败:{},{},{}", message, exchange, routingKey);
    }

    public void send(CommonMessageVo messageVo) {
        CorrelationData correlationId = new CorrelationData(messageVo.getId().toString());
        log.info("开始发送消息:{}", messageVo);
        rabbitTemplate.convertAndSend("topicExchange", key, messageVo.getMessage(), correlationId);
        // 模拟消息发送失败
        key = key.equals("key") ? "test" : "key";
    }
}
