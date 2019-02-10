package com.afitnerd.twilio_lametric.model.lametric;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LaMetricIcons {

    private Meta meta;
    private List<LaMetricIcon> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<LaMetricIcon> getData() {
        return data;
    }

    public void setData(List<LaMetricIcon> data) {
        this.data = data;
    }

    public static class Meta {

        @JsonProperty("total_icon_count")
        private int totalIconCount;

        @JsonProperty("page_size")
        private int pageSize;

        @JsonProperty("page_count")
        private int pageCount;

        private int page;

        public int getTotalIconCount() {
            return totalIconCount;
        }

        public void setTotalIconCount(int totalIconCount) {
            this.totalIconCount = totalIconCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }
    }
}
