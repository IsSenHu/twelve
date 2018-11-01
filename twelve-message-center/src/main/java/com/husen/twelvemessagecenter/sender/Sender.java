package com.husen.twelvemessagecenter.sender;

import com.alibaba.fastjson.JSON;
import com.husen.twelvemessagecenter.dao.vo.MessageVo;
import com.husen.twelvemessagecenter.service.MessageService;
import com.husen.vo.common.CommonMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

/**
 * Created by HuSen on 2018/11/1 13:18.
 */
@Slf4j
public abstract class Sender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    private final RabbitTemplate rabbitTemplate;
    private final MessageService messageService;

    public Sender(RabbitTemplate rabbitTemplate, MessageService messageService) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageService = messageService;
    }

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        try {
            if(ack) {
                messageService.MessageSendSuccess(correlationData.getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        try {
            String content = new String(message.getBody(), Charset.forName("UTF-8"));
            if(StringUtils.isNotBlank(content)) {
                log.info("消息:{} 没有找到目的地:{}->{}", content, exchange, routingKey);
                MessageVo messageVo = JSON.parseObject(content, MessageVo.class);
                messageService.messageGetLost(messageVo);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void send(CommonMessageVo commonMessageVo);
}
