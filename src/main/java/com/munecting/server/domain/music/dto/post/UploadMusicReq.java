package com.munecting.server.domain.music.dto.post;

import com.munecting.server.domain.music.entity.MusicGenre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UploadMusicReq {
    private String name;
    private String coverImg;
    private String musicPre;
    private String musicPull;
    private String artist;
    private MusicGenre genre;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
    private double pointX;
    private double pointY;
    private int memberId;
}
