package com.husen.twelvemessagecenter.sender;

import com.alibaba.fastjson.JSON;
import com.husen.twelvemessagecenter.constants.Constants;
import com.husen.twelvemessagecenter.dao.vo.MessageVo;
import com.husen.twelvemessagecenter.enums.RabbitMqMode;
import com.husen.twelvemessagecenter.service.MessageService;
import com.husen.vo.common.CommonMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by HuSen on 2018/11/1 21:06.
 */
@Slf4j
@Component
public class StudyNoteSenderImpl extends Sender {
    private final RabbitTemplate rabbitTemplate;
    private final MessageService messageService;

    @Autowired
    public StudyNoteSenderImpl(RabbitTemplate rabbitTemplate, MessageService messageService) {
        super(rabbitTemplate, messageService);
        this.rabbitTemplate = rabbitTemplate;
        this.messageService = messageService;
    }

    @Override
    public void send(CommonMessageVo commonMessageVo) {
        MessageVo messageVo = new MessageVo();
        try {
            CorrelationData correlationData = new CorrelationData(commonMessageVo.getId().toString());
            messageVo.setMessageId(commonMessageVo.getId());
            messageVo.setMessage(commonMessageVo.getMessage());
            messageVo.setRabbitMqMode(RabbitMqMode.TOPIC_MODE);
            messageVo.setQueue(Constants.LQueue.test.name());
            messageVo.setExchange(Constants.Exchange.topicExchange.name());
            messageVo.setKey(Constants.Key.key.name());
            messageVo.setSend(Boolean.FALSE);
            messageVo.setTimes(1);
            messageVo.setGetLost(Boolean.FALSE);
            messageVo.setCreateTime(LocalDateTime.now(ZoneId.of("GMT+8")));
            log.info("发送消息:{}", messageVo);
            // 持久化消息
            messageService.save(messageVo);
            rabbitTemplate.convertAndSend(messageVo.getExchange(), messageVo.getKey(), JSON.toJSONString(messageVo), correlationData);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
