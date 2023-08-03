package com.munecting.server.domain.music.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class MusicSearchPageRes {
    private List<MusicSearchRes> musicSearchRes;
    private int totalPage; //총페이지 수
}
