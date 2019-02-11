package com.afitnerd.twilio_lametric.service;

import com.afitnerd.twilio_lametric.model.repository.UserMessageInfo;
import com.afitnerd.twilio_lametric.model.twilio.TwilioRequest;
import com.afitnerd.twilio_lametric.model.twilio.TwilioResponse;
import com.afitnerd.twilio_lametric.repository.UserMessageInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TwilioRequestServiceImpl implements TwilioRequestService {

    private CodeService codeService;
    private UserMessageInfoRepository userMessageInfoRepository;
    private LaMetricService laMetricService;

    private static final Logger log = LoggerFactory.getLogger(TwilioRequestServiceImpl.class);

    public TwilioRequestServiceImpl(
        CodeService codeService,
        UserMessageInfoRepository userMessageInfoRepository,
        LaMetricService laMetricService
    ) {
        this.codeService = codeService;
        this.userMessageInfoRepository = userMessageInfoRepository;
        this.laMetricService = laMetricService;
    }


    @Override
    public TwilioResponse processMessage(TwilioRequest request) throws IOException {
        String body = (request.getBody() != null) ? request.getBody().trim() : "";

        // let's see if we can find this user in the repository
        UserMessageInfo userMessageInfo = userMessageInfoRepository.find(request.getFrom());

        TwilioResponse response = new TwilioResponse();
        // if not found, store their message and reply with a random number for the secret code reference
        if (userMessageInfo == null) {
            userMessageInfo = new UserMessageInfo();
            userMessageInfo.setFrom(request.getFrom());
            userMessageInfo.setMessage(body);
            int codeNum = codeService.generateCode();
            userMessageInfo.setCodeNum(codeNum);
            userMessageInfo.setCode(CodeService.CODES[codeNum]);
            userMessageInfoRepository.save(userMessageInfo);

            response.getMessage().setBody("Message received! Send back the secret code for (" + (codeNum+1) + ")");
            return response;
        }

        // if found, let's compare the code they sent to what we have in the repository
        if (userMessageInfo.getCode().equals(body.toLowerCase())) {
            // if the code matches, let's send their original message to the LaMetric
            laMetricService.sendMessage(userMessageInfo.getMessage());
            userMessageInfoRepository.delete(request.getFrom());
            response.getMessage().setBody("Yes!\nYou sent the correct secret code.\nTune into https://twitch.tv/afitnerd to see it on the big board!");
            return response;
        } else {
            // if the code doesn't match, let's remind them of the code they need
            response.getMessage().setBody(
                "Oh no!\n" +
                "The code you sent (" + body + ") is not correct!\n" +
                "You need to send the secret code for message: (" + (userMessageInfo.getCodeNum() + 1) + ")"
            );
        }
        return response;
    }
}
