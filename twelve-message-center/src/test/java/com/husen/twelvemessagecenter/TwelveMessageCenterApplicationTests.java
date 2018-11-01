package com.husen.twelvemessagecenter;

import com.husen.twelvemessagecenter.dao.mo.MessageMo;
import com.husen.twelvemessagecenter.dao.repository.MessageDao;
import com.husen.twelvemessagecenter.dao.repository.MessageRepository;
import com.husen.twelvemessagecenter.enums.RabbitMqMode;
import com.husen.twelvemessagecenter.sender.Sender;
import com.husen.twelvemessagecenter.sender.StudyNoteSenderImpl;
import com.husen.twelvemessagecenter.utils.SpringContextUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwelveMessageCenterApplicationTests {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageDao messageDao;
    @Test
    public void contextLoads() {
        MessageMo messageMo = new MessageMo();
        messageMo.setMessageId(1L);
        messageMo.setMessage("test");
        messageMo.setRabbitMqMode(RabbitMqMode.TOPIC_MODE);
        messageMo.setSend(Boolean.FALSE);
        messageMo.setGetLost(Boolean.FALSE);
        MessageMo save = messageRepository.save(messageMo);
        System.out.println(save);
    }

    @Test
    public void testGetBean() throws ClassNotFoundException {
        Sender bean = SpringContextUtils.getBean(StudyNoteSenderImpl.class.getName(), Sender.class);
        bean.sendAgain(null);
    }

}
