package com.husen.twelveredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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

    /**
     * 一次性去重
     * @param id 唯一ID标识
     * @return 是否重复
     */
    @GetMapping("/duplicateRemoval")
    private Mono<Boolean> duplicateRemoval(Long id) {
        return Mono.justOrEmpty(id)
                .map(x -> stringRedisTemplate.opsForHyperLogLog().add(MESSAGE_ID_LOGS, id.toString()))
                .map(y -> Long.valueOf(1L).equals(y))
                .switchIfEmpty(Mono.just(Boolean.FALSE));
    }
}
