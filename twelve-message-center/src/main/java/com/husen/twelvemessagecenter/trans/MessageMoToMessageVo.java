package com.husen.twelvemessagecenter.trans;

import com.husen.twelvemessagecenter.dao.mo.MessageMo;
import com.husen.twelvemessagecenter.dao.vo.MessageVo;
import org.springframework.stereotype.Component;
import java.util.Objects;
import java.util.function.Function;

/**
 * Created by HuSen on 2018/11/1 21:56.
 */
@Component
public class MessageMoToMessageVo implements Function<MessageMo, MessageVo> {
    @Override
    public MessageVo apply(MessageMo messageMo) {
        MessageVo messageVo = null;
        if(!Objects.isNull(messageMo)) {
            messageVo = new MessageVo();
            messageVo.setMessageId(messageMo.getMessageId());
            messageVo.setMessage(messageMo.getMessage());
            messageVo.setTimes(messageMo.getTimes());
            messageVo.setSend(messageMo.getSend());
            messageVo.setRabbitMqMode(messageMo.getRabbitMqMode());
            messageVo.setQueue(messageMo.getQueue());
            messageVo.setKey(messageMo.getKey());
            messageVo.setExchange(messageMo.getExchange());
            messageVo.setGetLost(messageMo.getGetLost());
            messageVo.setCreateTime(messageMo.getCreateTime());
            messageVo.setSenderClassName(messageMo.getSenderClassName());
        }
        return messageVo;
    }
}
