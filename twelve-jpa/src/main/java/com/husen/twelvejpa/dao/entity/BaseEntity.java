package com.husen.twelvejpa.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by HuSen on 2018/10/29 17:20.
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    private Long id;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
