package com.husen.twelvemessagecenter.api;

import com.husen.base.TableData;
import com.husen.twelvemessagecenter.dao.vo.MessageVo;
import com.husen.twelvemessagecenter.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Created by HuSen on 2018/11/6 10:26.
 */
@RestController
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/api/pageMessage")
    private Mono<TableData<MessageVo>> pageMessage(@RequestBody Map<String, Object> params) {
        System.out.println(params);
        return Mono.just(messageService.pageMessage());
    }
}
