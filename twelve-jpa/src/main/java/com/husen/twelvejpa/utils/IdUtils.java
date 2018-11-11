package com.husen.twelvejpa.utils;

import com.husen.utils.http.HttpConnectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;

/**
 * Created by HuSen on 2018/10/30 10:27.
 */
public class IdUtils {

    private static final String ID_URL = "http://118.24.38.46:12306/jian/genId";

    public static long getId() throws IOException {
        String resp = HttpConnectionUtils.doGet(ID_URL);
        if(StringUtils.isNotBlank(resp)) {
            return NumberUtils.createLong(resp);
        }
        // TODO 调用rest api获取Id失败，换用本地嵌入模式
        return -1L;
    }
}
