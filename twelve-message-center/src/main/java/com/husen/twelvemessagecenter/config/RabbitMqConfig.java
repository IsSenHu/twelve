package com.husen.twelvemessagecenter.config;

import com.husen.twelvemessagecenter.constants.Constants.*;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by HuSen on 2018/11/1 11:45.
 */
@Configuration
public class RabbitMqConfig {

    public class Test {
        @Bean
        public Queue queue() {
            return new Queue(LQueue.test.name(), true);
        }
        @Bean
        public TopicExchange topicExchange() {
            return new TopicExchange(Exchange.topicExchange.name());
        }
        @Bean
        public Binding binding() {
            return BindingBuilder.bind(queue()).to(topicExchange()).with(Key.key.name());
        }
    }
}
