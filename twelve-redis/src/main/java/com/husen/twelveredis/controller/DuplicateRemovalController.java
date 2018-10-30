package com.husen.twelveredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HuSen on 2018/10/30 17:18.
 */
@RestController
public class DuplicateRemovalController {
    private static final String MESSAGE_ID_LOGS = "message.id.logs";
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public DuplicateRemovalController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("/duplicateRemoval")
    private Boolean duplicateRemoval(Long id) {
        HyperLogLogOperations<String, String> logOperations = stringRedisTemplate.opsForHyperLogLog();
        Long add = logOperations.add(MESSAGE_ID_LOGS, id.toString());
        return Long.valueOf(1L).equals(add);
    }
}
