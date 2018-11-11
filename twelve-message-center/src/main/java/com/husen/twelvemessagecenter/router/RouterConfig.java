package com.husen.twelvemessagecenter.router;

import com.husen.base.Base;
import com.husen.base.CommonResponse;
import com.husen.base.TableData;
import com.husen.twelvemessagecenter.sender.StudyNoteSenderImpl;
import com.husen.twelvemessagecenter.service.MessageService;
import com.husen.vo.common.CommonMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by HuSen on 2018/11/1 15:52.
 */
@Configuration
@Slf4j
public class RouterConfig extends Base {
    private final StudyNoteSenderImpl studyNoteSender;
    private final MessageService messageService;
    @Autowired
    public RouterConfig(StudyNoteSenderImpl studyNoteSender, MessageService messageService) {
        this.studyNoteSender = studyNoteSender;
        this.messageService = messageService;
    }

    @Bean
    public RouterFunction<ServerResponse> test() {
        return route(POST("/api/sendStudyNote").and(accept(MediaType.APPLICATION_JSON_UTF8)),
                serverRequest -> ServerResponse.ok().body(
                        serverRequest.bodyToMono(CommonMessageVo.class)
                                     .doOnNext(studyNoteSender::send)
                                     .map(messageVo -> commonResponse(Boolean.TRUE, Constant.SUCCESS)),
                        CommonResponse.class)
                 );
    }
}
