package com.munecting.server.domain.music.dto.get;

import java.util.List;

public class YoutubeApiRes {
    private List<YoutubeApiItem> items;

    public List<YoutubeApiItem> getItems() {
        return items;
    }


    public static class YoutubeApiItem {
        private String title;
        private String videoId;
        public String getVideoId() {
            return videoId;
        }
    }
}
