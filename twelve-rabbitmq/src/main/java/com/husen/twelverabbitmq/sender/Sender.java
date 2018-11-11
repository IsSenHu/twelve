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
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

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
        try {
            // 就是在这里进行持久化 不因为异常而是找不到应该发送到哪里的消息
            Object object;
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message.getBody());
                 ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
                object = objectInputStream.readObject();
                CommonMessageVo commonMessageVo = (CommonMessageVo) object;
                HttpConnectionUtils.doGet("http://localhost:8080/failMessage?id=" + commonMessageVo.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
            // 这里有问题 TODO
        }
    }

    public void send(CommonMessageVo messageVo) {
        // 进行消息去重
        CorrelationData correlationId = new CorrelationData(messageVo.getId().toString());
        log.info("开始发送消息:{}", messageVo);
        rabbitTemplate.convertAndSend("topicExchange", key, messageVo, correlationId);
        // 模拟消息发送失败
        key = key.equals("key") ? "test" : "key";
    }
}
