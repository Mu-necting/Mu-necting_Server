package com.munecting.server.domain.music.controller;

import com.munecting.server.domain.music.dto.get.MusicSSearchRes;
import com.munecting.server.domain.music.dto.get.MusicSearchRes;
import com.munecting.server.domain.music.service.MusicService;
import com.munecting.server.global.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/archive")
@RequiredArgsConstructor
@Slf4j
public class MusicController {
    private final MusicService musicService;
    //앨범 하나 조회-music도메인으로 변경 예정
    @ResponseBody
    @GetMapping("/search/{search}")
    public BaseResponse<MusicSearchRes> getMusicPlusSearch(@PathVariable("search")String id){
        return new BaseResponse<>(musicService.getMusicPlusSearch(id));
    }
    //음악 검색 - music도메인으로 변경 예정
    @ResponseBody
    @GetMapping("/music")
    public BaseResponse<List<MusicSSearchRes>> getMusicSearch(String search){
        return new BaseResponse<>(musicService.getMusicSearch(search));
    }
}
