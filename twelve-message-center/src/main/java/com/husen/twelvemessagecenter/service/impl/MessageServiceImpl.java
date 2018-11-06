package com.husen.twelvemessagecenter.service.impl;

import com.husen.base.TableData;
import com.husen.twelvemessagecenter.dao.mo.MessageMo;
import com.husen.twelvemessagecenter.dao.repository.MessageDao;
import com.husen.twelvemessagecenter.dao.repository.MessageRepository;
import com.husen.twelvemessagecenter.dao.vo.MessageVo;
import com.husen.twelvemessagecenter.service.MessageService;
import com.husen.twelvemessagecenter.trans.MessageMoToMessageVo;
import com.husen.twelvemessagecenter.trans.MessageVoToMessageMo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Created by HuSen on 2018/11/1 13:34.
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageDao messageDao;
    private final MessageVoToMessageMo messageVoToMessageMo;
    private final MessageMoToMessageVo messageMoToMessageVo;
    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, MessageVoToMessageMo messageVoToMessageMo, MessageDao messageDao, MessageMoToMessageVo messageMoToMessageVo) {
        this.messageRepository = messageRepository;
        this.messageVoToMessageMo = messageVoToMessageMo;
        this.messageDao = messageDao;
        this.messageMoToMessageVo = messageMoToMessageVo;
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

    @Override
    public void messageAddTimes(MessageVo messageVo) {
        messageDao.messageAddTimes(messageVo);
    }

    @Override
    public TableData<MessageVo> pageMessage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MessageMo> page = messageRepository.findAll(pageable);
        TableData<MessageVo> tableData = new TableData<>();
        tableData.setPage(page.getNumber() + 1);
        tableData.setSize(page.getSize());
        tableData.setTotalPages(page.getTotalPages());
        tableData.setTotals(page.getTotalElements());
        tableData.setContent(page.getContent().stream().map(messageMoToMessageVo).collect(Collectors.toList()));
        return tableData;
    }
}
