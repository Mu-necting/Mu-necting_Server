package com.munecting.server.domain.archive.controller;

import com.munecting.server.domain.archive.dto.get.ArchivePlusRes;
import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.service.ArchiveService;
import com.munecting.server.global.config.BaseResponse;
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
    //주변에 있는 아카이브 조회
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<ArchiveRes>> getArchives(double x,double y,int range){
        return new BaseResponse<>(archiveService.getArchive(x,y,range));
    }
}
