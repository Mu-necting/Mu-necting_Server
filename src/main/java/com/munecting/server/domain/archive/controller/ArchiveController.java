package com.munecting.server.domain.archive.controller;

import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.dto.get.MapArchiveRes;
import com.munecting.server.domain.archive.dto.get.MyArchivePageRes;
import com.munecting.server.domain.archive.service.ArchiveService;
import com.munecting.server.global.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/archive")
@RequiredArgsConstructor
@Slf4j
public class ArchiveController {
    private final ArchiveService archiveService;

    //주변에 있는 아카이브 조회
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<ArchiveRes>> getArchives(double x,double y,int range){
        return new BaseResponse<>(archiveService.findNearArchive(x,y,range));
    }
    //내가 업로드한 아카이브 조회
    @ResponseBody
    @GetMapping("/{memberId}/")
    public BaseResponse<MyArchivePageRes> getMyArchives(@PathVariable("memberId")long memberId,
                                                        @PageableDefault(page = 0,size = 2) Pageable pageable){
        return new BaseResponse<>(archiveService.findArchiveByMember(memberId,pageable));
    }
    // 아카이브 맵 조회
    @ResponseBody
    @GetMapping("/map/")
    public BaseResponse<MapArchiveRes> getMapArchives(double x, double y, int range){
        return new BaseResponse(archiveService.findMapArchive(x,y,range));
    }
}
