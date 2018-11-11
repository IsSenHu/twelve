package com.husen.twelvejpa.controller;

import com.husen.base.CommonResponse;
import com.husen.twelvejpa.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HuSen on 2018/10/30 15:07.
 */
@RestController
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/sendMessage")
    private CommonResponse<String> sendMessage(String message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/ackMessage")
    private void ackMessage(Long id) {
        messageService.ackMessage(id);
    }

    @GetMapping("/failMessage")
    private void failMessage(Long id) {
        messageService.failMessage(id);
    }
}
