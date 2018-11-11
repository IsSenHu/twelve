package com.husen.twelverabbitmq.receiver;

import com.husen.utils.http.HttpConnectionUtils;
import com.husen.vo.common.CommonMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * Created by HuSen on 2018/10/30 16:08.
 */
@Component
@Slf4j
public class Receiver {
    @RabbitListener(queues = "base")
    public void onMessage(CommonMessageVo messageVo) {
        // 进行消息去重
        try {
            String resp = HttpConnectionUtils.doGet("http://localhost:8083/duplicateRemoval?id=" + messageVo.getId());
            if(StringUtils.isNotBlank(resp)) {
                if(!Boolean.valueOf(resp)) {
                    log.info("该消息重复，拒绝消费");
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("消费消息:{}", messageVo);
    }
}
