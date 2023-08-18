package com.munecting.server.domain.music.dto.get;

import com.munecting.server.domain.music.entity.MusicGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import se.michaelthelin.spotify.model_objects.specification.Image;

@Data
@AllArgsConstructor
public class MusicSearchRes {
    private String name;            //앨범 제목
    private String artist;          //가수 이름
    private String coverImg;        //앨범 이미지
    // private String musicPull; 유튜브 url
    private String musicPre;
}
