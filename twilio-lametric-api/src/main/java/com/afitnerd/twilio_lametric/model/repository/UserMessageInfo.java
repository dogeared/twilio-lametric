package com.afitnerd.twilio_lametric.model.repository;

import com.afitnerd.twilio_lametric.model.twilio.TwilioRequest;

public class UserMessageInfo {

    private String message;
    private String code;
    private int codeNum;
    private String from; // phone number = key

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(int codeNum) {
        this.codeNum = codeNum;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
