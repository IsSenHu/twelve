package com.husen.twelvemessagecenter.dao.repository;

import com.husen.twelvemessagecenter.dao.mo.MessageMo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by HuSen on 2018/11/1 10:26.
 */
@Repository
public interface MessageRepository extends MongoRepository<MessageMo, Long> {
    Optional<MessageMo> findByMessageId(long l);
}
