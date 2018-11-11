package com.husen.twelvejpa.service;

import com.husen.base.CommonResponse;

import java.io.IOException;

/**
 * Created by HuSen on 2018/10/30 15:08.
 */
public interface MessageService {
    CommonResponse<String> sendMessage(String message);

    void ackMessage(Long id);

    void failMessage(Long id);
}
