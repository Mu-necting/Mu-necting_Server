package com.munecting.server.domain.music.dto.get;

import lombok.Builder;
import lombok.Getter;
import se.michaelthelin.spotify.model_objects.specification.Image;

@Getter
public class MusicSearchRes {
    private String name;            //앨범 제목
    private String artist;          //가수 이름
    private String coverImg;        //앨범 이미지
    public MusicSearchRes(String name,String artist,String coverImg){
        this.name = name;
        this.artist = artist;
        this.coverImg = coverImg;
    }
}
