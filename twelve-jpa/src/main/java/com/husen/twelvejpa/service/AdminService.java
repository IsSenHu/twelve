package com.husen.twelvejpa.service;

import com.husen.twelvejpa.dao.entity.admin.AdminEntity;

import java.io.IOException;

/**
 * Created by HuSen on 2018/10/30 10:05.
 */
public interface AdminService {
    AdminEntity testTransaction() throws IOException;
}
