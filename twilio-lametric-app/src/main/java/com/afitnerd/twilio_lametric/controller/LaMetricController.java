package com.afitnerd.twilio_lametric.controller;

import com.afitnerd.twilio_lametric.model.lametric.LaMetricRequest;
import com.afitnerd.twilio_lametric.service.LaMetricService;
import org.apache.http.StatusLine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.afitnerd.twilio_lametric.config.AppConfig.API_PATH;

@RestController
@RequestMapping(API_PATH)
public class LaMetricController {

    LaMetricService laMetricService;

    public LaMetricController(LaMetricService laMetricService) {
        this.laMetricService = laMetricService;
    }

    @PostMapping("/message")
    public StatusLine message(@RequestBody LaMetricRequest request) throws IOException {
        return laMetricService.sendMessage(request);
    }
}
