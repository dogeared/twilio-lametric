package com.afitnerd.twilio_lametric.config;

import com.afitnerd.twilio_lametric.model.repository.UserMessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RedisConfig {

    private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Value("#{ @environment['redis.url'] ?: '' }")
    private String redisUrlStr;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {

        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setUsePool(true);

        setCreds(factory);

        return factory;
    }

    private void setCreds(JedisConnectionFactory factory) {
        if (StringUtils.isEmpty(redisUrlStr)) { return; }

        try {
            URI redisUri = new URI(redisUrlStr);
            factory.setHostName(redisUri.getHost());
            factory.setPort(redisUri.getPort());
            factory.setPassword(redisUri.getUserInfo().split(":", 2)[1]);
        } catch (URISyntaxException e) {
            log.error("Exception setting up JedisConnectionFactory: {}", e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Bean
    public RedisTemplate<String, UserMessageInfo> redisTemplate() {
        final RedisTemplate<String, UserMessageInfo> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer(UserMessageInfo.class));
        return template;
    }
}