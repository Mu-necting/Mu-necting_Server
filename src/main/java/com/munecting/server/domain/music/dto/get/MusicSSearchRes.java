package com.munecting.server.domain.music.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MusicSSearchRes {
    private String name;            //앨범 제목
    private String artist;          //가수 이름
    private String coverImg;        //앨범 이미지
    private String musicLink;       //음악 url
  //  private String genre;           //장르
}
