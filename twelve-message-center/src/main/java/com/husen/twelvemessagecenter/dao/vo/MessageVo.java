package com.husen.twelvemessagecenter.dao.vo;

import com.husen.twelvemessagecenter.enums.RabbitMqMode;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by HuSen on 2018/11/1 9:59.
 */
@Data
public class MessageVo implements Serializable {
    private Long messageId;

    private String message;

    private Boolean send;

    private Integer times;

    private RabbitMqMode rabbitMqMode;

    private String exchange;

    private String queue;

    private String key;

    private Boolean getLost;

    private LocalDateTime createTime;
}
