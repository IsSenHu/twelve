package com.husen.twelvejpa.controller;

import com.husen.twelvejpa.dao.entity.admin.AdminEntity;
import com.husen.twelvejpa.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by HuSen on 2018/10/30 10:04.
 */
@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 查询模式+补偿模式+快速失败模式
     * @return
     * @throws IOException
     */
    @GetMapping("/test")
    private AdminEntity testTransaction() throws IOException {
        return adminService.testTransaction();
    }
}
