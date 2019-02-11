package com.afitnerd.twilio_lametric.service;

import com.afitnerd.twilio_lametric.model.twilio.TwilioRequest;
import com.afitnerd.twilio_lametric.model.twilio.TwilioResponse;

import java.io.IOException;

public interface TwilioRequestService {

    TwilioResponse processMessage(TwilioRequest request) throws IOException;
}
