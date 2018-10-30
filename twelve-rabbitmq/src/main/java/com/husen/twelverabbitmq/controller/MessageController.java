package com.husen.twelverabbitmq.controller;

import com.husen.base.CommonResponse;
import com.husen.twelverabbitmq.service.MessageService;
import com.husen.vo.common.CommonMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HuSen on 2018/10/30 15:13.
 */
@RestController
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/sendMessage")
    private CommonResponse<Boolean> sendMessage(@RequestBody CommonMessageVo messageVo) {
        return messageService.sendMessage(messageVo);
    }
}
