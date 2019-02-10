package com.afitnerd.twilio_lametric.service;

import com.afitnerd.twilio_lametric.model.twilio.TwilioResponse;
import org.springframework.stereotype.Service;

@Service
public class TwilioResponseServiceImpl implements TwilioResponseService {


    public TwilioResponseServiceImpl() {

    }

    @Override
    public TwilioResponse getErrorResponse() {
        TwilioResponse response = new TwilioResponse();
        response
            .getMessage()
            .setBody("Send\n\n" + MESSAGE_COMMAND + "\n\nto get a message sent to the LaMetric.");
        return response;
    }
}
