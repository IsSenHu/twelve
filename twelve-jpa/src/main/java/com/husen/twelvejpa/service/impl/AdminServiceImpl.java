package com.husen.twelvejpa.service.impl;

import com.husen.twelvejpa.dao.entity.admin.AdminEntity;
import com.husen.twelvejpa.dao.repository.AdminRepository;
import com.husen.twelvejpa.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HuSen on 2018/10/30 10:05.
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminEntity testTransaction() {

        return null;
    }
}
