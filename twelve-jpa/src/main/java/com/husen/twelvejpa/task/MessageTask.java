package com.husen.twelvejpa.task;

import com.alibaba.fastjson.JSONObject;
import com.husen.twelvejpa.dao.entity.message.MessageEntity;
import com.husen.twelvejpa.dao.repository.MessageRepository;
import com.husen.utils.http.HttpConnectionUtils;
import com.husen.vo.common.CommonMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by HuSen on 2018/10/30 16:13.
 */
@Component
@Slf4j
public class MessageTask {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageTask(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Scheduled(fixedDelay = 120000L)
    private void run() {
        try {
            List<MessageEntity> messageEntities = messageRepository.findAllBySend(false);
            if(CollectionUtils.isNotEmpty(messageEntities)) {
                log.info("有消息发送失败，现在开始重试");
                for(MessageEntity entity : messageEntities) {
                    CommonMessageVo commonMessageVo = new CommonMessageVo();
                    commonMessageVo.setMessage(entity.getData());
                    commonMessageVo.setId(entity.getId());
                    HttpConnectionUtils.doPost("http://localhost:8082/sendMessage", JSONObject.toJSONString(commonMessageVo), true);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
