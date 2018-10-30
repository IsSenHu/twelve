package com.husen.twelvemybatis.dao.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by HuSen on 2018/10/29 18:33.
 */
@Data
public class BaseDto {
    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
