package com.munecting.server.domain.archive.controller;

import com.munecting.server.domain.archive.dto.get.ArchivePlusRes;
import com.munecting.server.domain.archive.dto.get.MusicSSearchRes;
import com.munecting.server.domain.archive.dto.get.MusicSearchRes;
import com.munecting.server.domain.archive.service.ArchiveService;
import com.munecting.server.global.config.BaseResponse;
import com.munecting.server.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/archive")
@RequiredArgsConstructor
@Slf4j
public class ArchiveController {
    private final ArchiveService archiveService;

    //아카이브 상세 조회
    @ResponseBody
    @GetMapping("/{archiveId}")
    public BaseResponse<ArchivePlusRes> getArchivePlus(@PathVariable("archiveId") Long id){
        return new BaseResponse<>(archiveService.getArchivePlus(id));
    }
    //앨범 하나 조회-music도메인으로 변경 예정
    @ResponseBody
    @GetMapping("/search/{search}")
    public BaseResponse<MusicSearchRes> getMusicPlusSearch(@PathVariable("search")String id){
        return new BaseResponse<>(archiveService.getMusicPlusSearch(id));
    }
    //음악 검색 - music도메인으로 변경 예정
    @ResponseBody
    @GetMapping("/music")
    public BaseResponse<List<MusicSSearchRes>> getMusicSearch(String search){
        return new BaseResponse<>(archiveService.getMusicSearch(search));
    }
}
