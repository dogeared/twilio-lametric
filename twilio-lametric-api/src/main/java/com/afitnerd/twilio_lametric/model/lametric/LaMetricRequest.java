package com.afitnerd.twilio_lametric.model.lametric;

import java.util.ArrayList;
import java.util.List;

public class LaMetricRequest {

    public static int MAX_MSG_LENGTH = 100;

    private List<Frame> frames = new ArrayList<>();

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    public static class Frame {

        private String text;
        private String icon;
        private int index;

        public Frame() {}

        public Frame(String text, String icon, int index) {
            this.text = text;
            this.icon = icon;
            this.index = index;
        }

        public String getText() {
            if (text == null) {
                return null;
            } else if (text.length() > MAX_MSG_LENGTH) {
                return text.substring(0, MAX_MSG_LENGTH);
            }
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
