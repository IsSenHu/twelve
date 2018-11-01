package com.husen.twelvemessagecenter.service.impl;

import com.husen.twelvemessagecenter.dao.repository.MessageDao;
import com.husen.twelvemessagecenter.dao.repository.MessageRepository;
import com.husen.twelvemessagecenter.dao.vo.MessageVo;
import com.husen.twelvemessagecenter.service.MessageService;
import com.husen.twelvemessagecenter.trans.MessageVoToMessageMo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HuSen on 2018/11/1 13:34.
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageDao messageDao;
    private final MessageVoToMessageMo messageVoToMessageMo;
    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, MessageVoToMessageMo messageVoToMessageMo, MessageDao messageDao) {
        this.messageRepository = messageRepository;
        this.messageVoToMessageMo = messageVoToMessageMo;
        this.messageDao = messageDao;
    }

    @Override
    public void save(MessageVo messageVo) {
        messageRepository.save(messageVoToMessageMo.apply(messageVo));
    }

    @Override
    public void messageGetLost(MessageVo messageVo) {
        messageDao.messageGetLost(messageVo.getMessageId());
    }

    @Override
    public void MessageSendSuccess(String messageId) {
        messageDao.MessageSendSuccess(messageId);
    }
}
