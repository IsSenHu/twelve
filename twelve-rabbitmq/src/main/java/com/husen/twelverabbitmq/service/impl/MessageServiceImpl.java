package com.husen.twelverabbitmq.service.impl;

import com.husen.base.Base;
import com.husen.base.CommonResponse;
import com.husen.twelverabbitmq.sender.Sender;
import com.husen.twelverabbitmq.service.MessageService;
import com.husen.vo.common.CommonMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HuSen on 2018/10/30 15:37.
 */
@Service
@Slf4j
public class MessageServiceImpl extends Base implements MessageService {
    private final Sender sender;

    @Autowired
    public MessageServiceImpl(Sender sender) {
        this.sender = sender;
    }

    @Override
    public CommonResponse<Boolean> sendMessage(CommonMessageVo messageVo) {
        try {
            sender.send(messageVo);
        }catch (Exception e) {
            e.printStackTrace();
            log.info("消息发送失败，异常信息:{}", e.getMessage());
            return commonResponse(false, Constant.FAIL);
        }
        return commonResponse(true, Constant.SUCCESS);
    }
}
