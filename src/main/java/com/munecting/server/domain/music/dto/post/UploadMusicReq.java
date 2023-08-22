package com.munecting.server.domain.music.dto.post;

import com.munecting.server.domain.music.entity.MusicGenre;
import jakarta.servlet.http.HttpServletRequest;
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
    private int plusTime;
    private double pointX;
    private double pointY;
}
