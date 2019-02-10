package com.afitnerd.twilio_lametric.model.lametric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LaMetricIcon {

    private int id;
    private String title;
    private String code;
    private String type;
    private String category;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static LaMetricIcon defaultIcon() {
        LaMetricIcon icon = new LaMetricIcon();
        icon.setId(1190);
        icon.setTitle("heartbeat");
        icon.setCode("a1190");
        icon.setType("movie");
        return icon;
    }
}
