package com.husen.twelvemybatis.controller;

import com.husen.twelvemybatis.dao.dto.admin.AdminDto;
import com.husen.twelvemybatis.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HuSen on 2018/10/30 10:53.
 */
@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/saveEnable")
    private AdminDto saveEnable(Long id) {
        return adminService.saveEnable(id);
    }

    @GetMapping("/findById")
    private AdminDto findById(Long id) {
        return adminService.findById(id);
    }
}
