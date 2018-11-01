package com.husen.twelvemessagecenter.task;

import com.husen.twelvemessagecenter.dao.mo.MessageMo;
import com.husen.twelvemessagecenter.dao.repository.MessageRepository;
import com.husen.twelvemessagecenter.sender.Sender;
import com.husen.twelvemessagecenter.trans.MessageMoToMessageVo;
import com.husen.twelvemessagecenter.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Created by HuSen on 2018/11/1 20:41.
 */
@Component
@Slf4j
public class MessageRetryTask {
    private final MessageRepository messageRepository;
    private final MessageMoToMessageVo messageMoToMessageVo;
    @Autowired
    public MessageRetryTask(MessageRepository messageRepository, MessageMoToMessageVo messageMoToMessageVo) {
        this.messageRepository = messageRepository;
        this.messageMoToMessageVo = messageMoToMessageVo;
    }

    @Scheduled(fixedDelay = 120000L, initialDelay = 3000L)
    public void run() {
        int page = 0;
        int totalPages = 0;
        final int size = 20;
        while (page <= totalPages) {
            Sort sort = Sort.by(Sort.Order.asc("createTime"));
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<MessageMo> moPage = messageRepository.findAllBySendOrGetLost(false, true, pageable);
            totalPages = moPage.getTotalPages();
            log.info("当前第{}页，总共{}页", page, totalPages);
            moPage.get().forEach(this::send);
            page++;
        }
    }

    private void send(MessageMo messageMo) {
        try {
            Optional.of(SpringContextUtils.getBean(messageMo.getSenderClassName(), Sender.class))
                    .ifPresent(sender -> sender.sendAgain(messageMoToMessageVo.apply(messageMo)));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
