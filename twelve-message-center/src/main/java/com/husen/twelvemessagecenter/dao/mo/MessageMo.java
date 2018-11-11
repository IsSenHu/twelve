package com.husen.twelvemessagecenter.dao.mo;

import com.husen.twelvemessagecenter.enums.RabbitMqMode;
import com.husen.twelvemessagecenter.sender.Sender;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by HuSen on 2018/11/1 9:59.
 */
@Document(collection = "reMessage")
@Data
public class MessageMo implements Serializable {

    @Field("_id")
    private Long messageId;

    @Field("message")
    private String message;

    @Field("send")
    private Boolean send;

    @Field("times")
    private Integer times;

    @Field("rabbitMqMode")
    private RabbitMqMode rabbitMqMode;

    @Field("exchange")
    private String exchange;

    @Field("queue")
    private String queue;

    @Field("key")
    private String key;

    @Field("getLost")
    private Boolean getLost;

    @Field("createTime")
    private LocalDateTime createTime;

    @Field("senderClassName")
    private String senderClassName;
}
