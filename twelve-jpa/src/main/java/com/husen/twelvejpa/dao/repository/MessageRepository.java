package com.husen.twelvejpa.dao.repository;

import com.husen.twelvejpa.dao.entity.message.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HuSen on 2018/10/30 15:03.
 */
@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findAllBySend(boolean b);
}
