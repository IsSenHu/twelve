package com.husen.twelvejpa.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.husen.base.Base;
import com.husen.base.CommonResponse;
import com.husen.twelvejpa.dao.entity.message.MessageEntity;
import com.husen.twelvejpa.dao.repository.MessageRepository;
import com.husen.twelvejpa.service.MessageService;
import com.husen.twelvejpa.utils.IdUtils;
import com.husen.utils.http.HttpConnectionUtils;
import com.husen.vo.common.CommonMessageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by HuSen on 2018/10/30 15:08.
 */
@Service
public class MessageServiceImpl extends Base implements MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public CommonResponse<String> sendMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return commonResponse(null, Constant.PARAM_EXCEPTION);
        }
        try {
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setId(IdUtils.getId());
            messageEntity.setData(message);
            messageEntity.setSend(false);
            messageEntity.setCreateTime(LocalDateTime.now());
            messageEntity.setUpdateTime(LocalDateTime.now());
            messageRepository.save(messageEntity);
            CommonMessageVo commonMessageVo = new CommonMessageVo();
            commonMessageVo.setId(messageEntity.getId());
            commonMessageVo.setMessage(message);
            HttpConnectionUtils.doPost("http://localhost:8082/sendMessage", JSONObject.toJSONString(commonMessageVo), true);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return commonResponse(message, Constant.SUCCESS);
    }

    @Override
    public void ackMessage(Long id) {
        Optional<MessageEntity> messageEntity = messageRepository.findById(id);
        messageEntity.ifPresent(message -> {
            message.setSend(true);
            messageRepository.save(message);
        });
    }
}
