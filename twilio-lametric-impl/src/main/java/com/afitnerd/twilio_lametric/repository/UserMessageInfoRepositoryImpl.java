package com.afitnerd.twilio_lametric.repository;

import com.afitnerd.twilio_lametric.model.repository.UserMessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class UserMessageInfoRepositoryImpl implements UserMessageInfoRepository {

    private static Logger log = LoggerFactory.getLogger(UserMessageInfoRepositoryImpl.class);

    private static final String KEY_PREFIX = "user";

    private RedisTemplate<String, UserMessageInfo> sessionInfoTemplate;

    public UserMessageInfoRepositoryImpl(RedisTemplate<String, UserMessageInfo> sessionInfoTemplate) {
        this.sessionInfoTemplate = sessionInfoTemplate;
        sessionInfoTemplate.setKeySerializer(new StringRedisSerializer());
    }

    @Override
    public synchronized void save(UserMessageInfo userMessageInfo) {
        BoundValueOperations<String, UserMessageInfo> boundValueOperations =
            sessionInfoTemplate.boundValueOps(buildKey(userMessageInfo.getFrom()));
        boundValueOperations.set(userMessageInfo);
        boundValueOperations.expire(1, TimeUnit.HOURS);
    }

    @Override
    public UserMessageInfo find(String fromPhoneNumber) {
        return sessionInfoTemplate.boundValueOps(buildKey(fromPhoneNumber)).get();
    }

    @Override
    public synchronized void delete(String fromPhoneNumber) {
        sessionInfoTemplate.delete(buildKey(fromPhoneNumber));
    }

    private String buildKey(String id) {
        return KEY_PREFIX + ":" + id;
    }
}
