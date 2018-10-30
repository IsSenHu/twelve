package com.husen.twelvemybatis.dao.mapper;

import com.husen.twelvemybatis.dao.dto.admin.AdminDto;
import org.springframework.stereotype.Repository;

/**
 * Created by HuSen on 2018/10/30 9:54.
 */
@Repository
public interface AdminMapper {
    void save(AdminDto adminDto);

    AdminDto findById(Long id);
}
