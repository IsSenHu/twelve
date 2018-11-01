package com.husen.twelvemessagecenter.trans;

import com.husen.twelvemessagecenter.dao.mo.MessageMo;
import com.husen.twelvemessagecenter.dao.vo.MessageVo;
import org.springframework.stereotype.Component;
import java.util.Objects;
import java.util.function.Function;

/**
 * Created by HuSen on 2018/11/1 13:37.
 */
@Component
public class MessageVoToMessageMo implements Function<MessageVo, MessageMo> {
    @Override
    public MessageMo apply(MessageVo messageVo) {
        MessageMo messageMo = null;
        if(!Objects.isNull(messageVo)) {
            messageMo = new MessageMo();
            messageMo.setMessageId(messageVo.getMessageId());
            messageMo.setMessage(messageVo.getMessage());
            messageMo.setTimes(messageVo.getTimes());
            messageMo.setSend(messageVo.getSend());
            messageMo.setRabbitMqMode(messageVo.getRabbitMqMode());
            messageMo.setQueue(messageVo.getQueue());
            messageMo.setKey(messageVo.getKey());
            messageMo.setExchange(messageVo.getExchange());
            messageMo.setGetLost(messageVo.getGetLost());
            messageMo.setCreateTime(messageVo.getCreateTime());
            messageMo.setSenderClassName(messageVo.getSenderClassName());
        }
        return messageMo;
    }
}
