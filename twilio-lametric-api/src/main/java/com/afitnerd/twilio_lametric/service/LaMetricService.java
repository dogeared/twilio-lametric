package com.afitnerd.twilio_lametric.service;

import com.afitnerd.twilio_lametric.model.lametric.LaMetricIcon;
import com.afitnerd.twilio_lametric.model.lametric.LaMetricRequest;
import java.io.IOException;
import java.util.Optional;

import org.apache.http.StatusLine;

public interface LaMetricService {

    String LAMETRIC_URL = "https://developer.lametric.com";
    String LAMETRIC_WIDGET_API_URI = "/api/v1/dev/widget/update";
    String LAMETRIC_ICONS_API_URI = "/api/v2/icons";

    StatusLine sendMessage(LaMetricRequest request) throws IOException;
    StatusLine sendMessage(String msg, String iconTitle) throws IOException;
    StatusLine sendMessage(String msg) throws IOException;
    Optional<LaMetricIcon> findLaMetricIcon(String searchStr);
}
