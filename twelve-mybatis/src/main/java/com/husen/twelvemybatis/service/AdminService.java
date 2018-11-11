package com.husen.twelvemybatis.service;

import com.husen.twelvemybatis.dao.dto.admin.AdminDto;

/**
 * Created by HuSen on 2018/10/30 10:53.
 */
public interface AdminService {
    AdminDto saveEnable(Long id);

    AdminDto findById(Long id);
}
