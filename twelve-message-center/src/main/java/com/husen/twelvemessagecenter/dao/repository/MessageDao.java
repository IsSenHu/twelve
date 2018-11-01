package com.husen.twelvemessagecenter.dao.repository;

import com.husen.twelvemessagecenter.dao.mo.MessageMo;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by HuSen on 2018/11/1 15:02.
 */
@Slf4j
@Repository
public class MessageDao {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MessageDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void messageGetLost(Long messageId) {
        UpdateResult result = mongoTemplate.upsert(
                query(where("messageId").is(messageId)), Update.update("getLost", Boolean.TRUE), MessageMo.class
        );
        log.debug("UpdateResult:{}", result);
    }

    public void MessageSendSuccess(String messageId) {
        UpdateResult result = mongoTemplate.upsert(
                query(where("messageId").is(Long.valueOf(messageId))), Update.update("send", Boolean.TRUE), MessageMo.class
        );
        log.debug("UpdateResult:{}", result);
    }
}
