package com.munecting.server.domain.music.dto.post;

import java.time.LocalDateTime;

public class UploadMusicReq {
    private String artist;
    //private String genre; 태그와 장르의 구분이 좀 필요할 듯합니다.
    private String coverImg;
    private String musicLink;
    private String name;
    private LocalDateTime createAt;
}
