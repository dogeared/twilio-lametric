package com.afitnerd.twilio_lametric.service;

import com.afitnerd.twilio_lametric.model.twilio.TwilioResponse;

public interface TwilioResponseService {

    public static final String MESSAGE_COMMAND = "msg";

    TwilioResponse getErrorResponse();
}
