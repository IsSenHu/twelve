package com.husen.twelverabbitmq.service;

import com.husen.base.CommonResponse;
import com.husen.vo.common.CommonMessageVo;

/**
 * Created by HuSen on 2018/10/30 15:36.
 */
public interface MessageService {
    CommonResponse<Boolean> sendMessage(CommonMessageVo messageVo);
}
