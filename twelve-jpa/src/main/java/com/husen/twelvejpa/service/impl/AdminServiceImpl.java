package com.husen.twelvejpa.service.impl;

import com.husen.twelvejpa.dao.entity.admin.AdminEntity;
import com.husen.twelvejpa.dao.repository.AdminRepository;
import com.husen.twelvejpa.enums.AdminStatus;
import com.husen.twelvejpa.service.AdminService;
import com.husen.twelvejpa.utils.IdUtils;
import com.husen.utils.http.HttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by HuSen on 2018/10/30 10:05.
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AdminEntity testTransaction() throws IOException {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setId(IdUtils.getId());
        adminEntity.setAdminName("test2");
        adminEntity.setPassword("123456");
        adminEntity.setAdminStatus(AdminStatus.ENABLE);
        adminEntity.setCreateTime(LocalDateTime.now());
        adminEntity.setUpdateTime(LocalDateTime.now());
        adminRepository.save(adminEntity);
        String json = "{\"id\":2333909443672015869,\"createTime\":\"2018-10-30T12:48:25\",\"updateTime\":\"2018-10-30T12:48:25\",\"adminName\":\"test1\",\"password\":\"123456\",\"adminStatus\":1}";
        try {
            HttpConnectionUtils.doPost("http://localhost:8081/saveEnable?id=" + adminEntity.getId(), null, true);
            return adminEntity;
        }catch (IOException e) {
            log.error("请求失败，原因:{}", e.getMessage());
            String result = HttpConnectionUtils.doGet("http://localhost:8081/findById?id=" + adminEntity.getId());
            log.info("查询是否执行结果:{}", result);
            if(StringUtils.isNotBlank(result)) {
                log.info("服务2端已执行成功，无需重试");
                return adminEntity;
            }
            log.info("进行重试");
            try {
                HttpConnectionUtils.doPost("http://localhost:8081/saveEnable?id=" + adminEntity.getId(), json, true);
                log.info("重试成功");
                return adminEntity;
            }catch (IOException ie) {
                log.info("重试失败");
                throw new RuntimeException();
            }
        }
    }
}
