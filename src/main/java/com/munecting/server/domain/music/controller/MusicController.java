package com.munecting.server.domain.music.controller;

import com.munecting.server.domain.music.dto.get.MusicSearchPageRes;
import com.munecting.server.domain.music.dto.get.MusicSearchRes;
import com.munecting.server.domain.music.service.MusicService;
import com.munecting.server.global.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
@Slf4j
public class MusicController {
    private final MusicService musicService;

    //음악 검색 - music도메인으로 변경 예정
    @ResponseBody
    @GetMapping("")
    public BaseResponse<MusicSearchPageRes> getMusicSearch(String search, int page){
        return new BaseResponse<>(musicService.getMusicSearch(search,page));
    }
}
