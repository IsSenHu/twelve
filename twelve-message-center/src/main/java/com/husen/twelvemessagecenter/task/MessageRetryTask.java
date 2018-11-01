package com.husen.twelvemessagecenter.task;

import com.husen.twelvemessagecenter.dao.mo.MessageMo;
import com.husen.twelvemessagecenter.dao.repository.MessageDao;
import com.husen.twelvemessagecenter.dao.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by HuSen on 2018/11/1 20:41.
 */
@Component
@Slf4j
public class MessageRetryTask {
    private final MessageRepository messageRepository;
    private final MessageDao messageDao;
    @Autowired
    public MessageRetryTask(MessageRepository messageRepository, MessageDao messageDao) {
        this.messageRepository = messageRepository;
        this.messageDao = messageDao;
    }

    @Scheduled(fixedDelay = 120000L)
    public void run() {
        int page = 0;
        int totalPages = 0;
        final int size = 20;
        while (page++ <= totalPages) {
            Sort sort = Sort.by(Sort.Order.asc("createTime"));
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<MessageMo> moPage = messageRepository.findAllBySendOrGetLost(false, true, pageable);
            totalPages = moPage.getTotalPages();
            moPage.get().forEach(this::send);
        }
    }

    private void send(MessageMo messageMo) {

    }
}
