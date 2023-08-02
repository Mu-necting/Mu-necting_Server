package com.munecting.server.domain.archive.dto.post;

import com.munecting.server.domain.music.dto.post.UploadMusicReq;

import java.time.LocalDateTime;

public class UploadArchiveReq {
    private UploadMusicReq uploadMusicReq;
    private Float pointX;
    private Float pointY;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String writing;
}
