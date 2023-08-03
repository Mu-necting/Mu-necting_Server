package com.munecting.server.domain.music.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.munecting.server.domain.music.dto.get.MusicSearchPageRes;
import com.munecting.server.domain.music.dto.get.MusicSearchRes;
import com.munecting.server.domain.music.dto.post.UploadMusicReq;
import com.munecting.server.domain.music.service.MusicService;
import com.munecting.server.global.config.BaseResponse;
import com.munecting.server.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musics")
@RequiredArgsConstructor
@Slf4j
public class MusicController {
    private final MusicService musicService;

    //음악 검색
    @ResponseBody
    @GetMapping("")
    public BaseResponse<MusicSearchPageRes> getMusicSearch(String search, int page){
        return new BaseResponse<>(musicService.getMusicSearch(search,page));
    }
    //아카이브 업로드
    @ResponseBody
    @PostMapping("")
    public BaseResponse postMusicArchive(@RequestBody UploadMusicReq uploadMusicReq){
        musicService.postMusicArchive(uploadMusicReq);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
