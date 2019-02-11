package com.afitnerd.twilio_lametric.service;

import com.afitnerd.twilio_lametric.model.lametric.LaMetricIcon;
import com.afitnerd.twilio_lametric.model.lametric.LaMetricIcons;
import com.afitnerd.twilio_lametric.model.lametric.LaMetricRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;


@Service
public class LaMetricServiceImpl implements LaMetricService {

    @Value("#{ @environment['lametric.app.id'] }")
    String appId;

    @Value("#{ @environment['lametric.app.accesstoken'] }")
    String accessToken;

    private ObjectMapper mapper = new ObjectMapper();
    private TypeReference<LaMetricIcons> laMetricIconsTypeRef = new TypeReference<LaMetricIcons>() {};

    private LaMetricIcons icons;

    private static final Logger log = LoggerFactory.getLogger(LaMetricServiceImpl.class);

    @PostConstruct
    void setup() {
        this.icons = getIcons();
    }

    @Override
    public StatusLine sendMessage(LaMetricRequest request) throws IOException {
        HttpResponse response = Request.Post(LAMETRIC_URL + LAMETRIC_WIDGET_API_URI + appId)
            .addHeader("X-Access-Token", accessToken)
            .bodyString(mapper.writeValueAsString(request), ContentType.APPLICATION_JSON)
            .execute()
            .returnResponse();
        return response.getStatusLine();
    }

    @Override
    public StatusLine sendMessage(String msg, String iconTitle) throws IOException {
        LaMetricIcon icon = findLaMetricIcon(iconTitle).orElse(LaMetricIcon.defaultIcon());
        LaMetricRequest request = new LaMetricRequest();
        request.getFrames().add(new LaMetricRequest.Frame(msg, icon.getCode(), 0));
        return sendMessage(request);
    }

    @Override
    public StatusLine sendMessage(String msg) throws IOException {
        if (msg.startsWith("-")) {
            int spaceIndex = msg.indexOf(" ");
            String iconTitle = msg.substring(1, spaceIndex);
            msg = msg.substring(spaceIndex+1);
            return sendMessage(msg, iconTitle);
        }
        return sendMessage(msg, LaMetricIcon.defaultIcon().getTitle());
    }

    @Override
    public Optional<LaMetricIcon> findLaMetricIcon(String searchStr) {
        if (isEmptyIcons() || StringUtils.isEmpty(searchStr)) {
            return null;
        }
        return icons.getData().stream().filter(i -> i.getTitle().toLowerCase().contains(searchStr)).findFirst();
    }

    private LaMetricIcons getIcons() {
        try {
            InputStream is = Request.Get(LAMETRIC_URL + LAMETRIC_ICONS_API_URI)
                .addHeader("X-Access-Token", accessToken)
                .execute()
                .returnContent()
                .asStream();
            return mapper.readValue(is, laMetricIconsTypeRef);

        } catch (IOException e) {
            log.error("Coulcnd't get icons: {}", e.getMessage(), e);
            return null;
        }
    }

    private boolean isEmptyIcons() {
        return icons == null || icons.getData() == null || icons.getData().size() == 0;
    }
}
