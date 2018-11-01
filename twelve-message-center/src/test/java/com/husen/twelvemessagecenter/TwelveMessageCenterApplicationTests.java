package com.husen.twelvemessagecenter;

import com.husen.twelvemessagecenter.dao.mo.MessageMo;
import com.husen.twelvemessagecenter.dao.repository.MessageRepository;
import com.husen.twelvemessagecenter.enums.RabbitMqMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwelveMessageCenterApplicationTests {
    @Autowired
    private MessageRepository messageRepository;
    @Test
    public void contextLoads() {
        MessageMo messageMo = new MessageMo();
        messageMo.setMessageId(1L);
        messageMo.setExchange("test");
        messageMo.setKey("test");
        messageMo.setMessage("test");
        messageMo.setQueue("test");
        messageMo.setRabbitMqMode(RabbitMqMode.DIRECT_MODE);
        messageMo.setSend(false);
        messageMo.setTimes(1);
        messageRepository.save(messageMo);

        Optional<MessageMo> byId = messageRepository.findByMessageId(1L);
        byId.ifPresent(System.out::println);
    }

}
