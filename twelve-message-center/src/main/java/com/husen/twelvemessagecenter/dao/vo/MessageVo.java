package com.husen.twelvemessagecenter.dao.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.husen.twelvemessagecenter.enums.RabbitMqMode;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by HuSen on 2018/11/1 9:59.
 */
@Data
public class MessageVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long messageId;

    private String message;

    private Boolean send;

    private Integer times;

    private RabbitMqMode rabbitMqMode;

    private String exchange;

    private String queue;

    private String key;

    private Boolean getLost;

    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm", timezone = "GMT+8")
    private LocalDateTime createTime;

    private String senderClassName;
}
