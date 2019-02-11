package com.afitnerd.twilio_lametric.repository;

import com.afitnerd.twilio_lametric.model.repository.UserMessageInfo;

public interface UserMessageInfoRepository {

    void save(UserMessageInfo userMessageInfo);
    void delete(String fromPhoneNumber);
    UserMessageInfo find(String fromPhoneNumber);
}
