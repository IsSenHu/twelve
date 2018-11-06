package com.husen.twelvemessagecenter.service;

import com.husen.base.TableData;
import com.husen.twelvemessagecenter.dao.vo.MessageVo;

/**
 * Created by HuSen on 2018/11/1 13:34.
 */
public interface MessageService {
    void save(MessageVo messageVo);

    void messageGetLost(MessageVo messageVo);

    void MessageSendSuccess(String messageId);

    void messageAddTimes(MessageVo messageVo);

    TableData<MessageVo> pageMessage();
}
