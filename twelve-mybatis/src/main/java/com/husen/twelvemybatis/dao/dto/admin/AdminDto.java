package com.husen.twelvemybatis.dao.dto.admin;

import com.husen.twelvemybatis.dao.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by HuSen on 2018/10/29 18:32.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminDto extends BaseDto {
    private String adminName;

    private String password;

    private Integer adminStatus;
}
