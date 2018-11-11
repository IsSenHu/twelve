package com.husen.twelvejpa.dao.repository;

import com.husen.twelvejpa.dao.entity.admin.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by HuSen on 2018/10/29 17:52.
 */
@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

}
