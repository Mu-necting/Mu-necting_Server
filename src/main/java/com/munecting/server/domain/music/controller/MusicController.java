package com.munecting.server.domain.music.controller;

import com.munecting.server.domain.archive.service.ArchiveService;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.music.dto.get.MusicSearchPageRes;
import com.munecting.server.domain.music.dto.post.UploadMusicReq;
import com.munecting.server.domain.music.service.MusicService;
import com.munecting.server.global.config.BaseResponse;
import com.munecting.server.global.config.BaseResponseStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/musics")
@RequiredArgsConstructor
@Slf4j
public class MusicController {
    private final MusicService musicService;
    private final ArchiveService archiveService;
    //음악 검색
    @ResponseBody
    @GetMapping("")
    public BaseResponse<MusicSearchPageRes> getMusicSearch(String search, int page){
        return new BaseResponse<>(musicService.getMusicSearch(search,page));
    }
    //아카이브 업로드
    @ResponseBody
    @Transactional
    @PostMapping("")
    public BaseResponse postMusicArchive(@RequestBody UploadMusicReq uploadMusicReq){
        archiveService.saveArchive(uploadMusicReq,musicService.saveMusic(uploadMusicReq));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
