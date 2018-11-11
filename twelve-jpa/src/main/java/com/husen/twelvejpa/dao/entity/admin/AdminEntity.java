package com.husen.twelvejpa.dao.entity.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.husen.twelvejpa.converter.AdminConverter;
import com.husen.twelvejpa.dao.entity.BaseEntity;
import com.husen.twelvejpa.enums.AdminStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;

/**
 * Created by HuSen on 2018/10/29 17:19.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_admin", indexes = {
        @Index(name = "index_admin_name", columnList = "admin_name", unique = true),
        @Index(name = "index_admin_status", columnList = "admin_status", unique = false)
})
public class AdminEntity extends BaseEntity {

    @Column(name = "admin_name", unique = true, nullable = false, length = 30)
    private String adminName;

    @Column(name = "password", nullable = false, length = 16)
    private String password;

    @JsonIgnore
    @Column(name = "admin_status", nullable = false, length = 1, columnDefinition = "int not null COMMENT '" + AdminStatus.Doc + "'")
    @Convert(converter = AdminConverter.class)
    private AdminStatus adminStatus;
}
