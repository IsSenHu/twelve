package com.husen.twelvemybatis.service.impl;

import com.husen.twelvemybatis.dao.dto.admin.AdminDto;
import com.husen.twelvemybatis.dao.mapper.AdminMapper;
import com.husen.twelvemybatis.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Created by HuSen on 2018/10/30 10:53.
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminDto saveEnable(Long id) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(id);
        adminDto.setAdminName("test1");
        adminDto.setPassword("123456");
        adminDto.setCreateTime(LocalDateTime.now());
        adminDto.setUpdateTime(LocalDateTime.now());
        adminDto.setAdminStatus(1);
        adminMapper.save(adminDto);
        return adminDto;
    }

    @Override
    public AdminDto findById(Long id) {
        return adminMapper.findById(id);
    }
}
