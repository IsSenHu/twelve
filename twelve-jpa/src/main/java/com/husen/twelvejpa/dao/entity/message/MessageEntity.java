package com.husen.twelvejpa.dao.entity.message;

import com.husen.twelvejpa.dao.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by HuSen on 2018/10/30 14:52.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_message")
public class MessageEntity extends BaseEntity {

    @Column(name = "data", nullable = false, length = 1000)
    private String data;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "repeat_time")
    private LocalDateTime repeatTime;

    @Column(name = "send")
    private Boolean send;
}
