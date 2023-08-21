package com.munecting.server.domain.music.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class YoutubeService {
    @Value("${youtube.apiKey}")
    private String apiKey;

    public String getSongLink(String name, String artist) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(artist)) {
            return null;
        }

        try {
            String searchQuery = name + " " + artist;
            // URL 인코딩 적용
            searchQuery = URLEncoder.encode(searchQuery, "UTF-8");
            String searchUrl = "https://www.googleapis.com/youtube/v3/search" +
                    "?part=snippet&type=video&maxResults=1&q=" + searchQuery + "&key=" + apiKey;

            URL url = new URL(searchUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(connection.getInputStream());
                JsonNode items = jsonResponse.get("items");
                if (items.size() > 0) {
                    JsonNode item = items.get(0);
                    JsonNode id = item.get("id");
                    String videoId = id.get("videoId").asText();
                    return "https://www.youtube.com/watch?v=" + videoId;
                }
            } else {
                System.out.println("Error - Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
