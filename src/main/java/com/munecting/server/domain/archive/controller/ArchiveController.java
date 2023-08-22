package com.munecting.server.domain.archive.controller;

import com.munecting.server.domain.archive.dto.get.ArchiveDetailRes;
import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.dto.get.MapArchiveRes;
import com.munecting.server.domain.archive.dto.get.MyArchivePageRes;
import com.munecting.server.domain.archive.service.ArchiveService;
import com.munecting.server.global.config.BaseResponse;
import com.munecting.server.global.config.BaseResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
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
    @GetMapping("/my/")
    public BaseResponse<MyArchivePageRes> getMyArchives(HttpServletRequest request,
                                                        @PageableDefault(page = 0,size = 2) Pageable pageable) throws Exception {
        return new BaseResponse<>(archiveService.findArchiveByMember(request,pageable));
    }
    // 아카이브 맵 조회
    @ResponseBody
    @GetMapping("/map/")
    public BaseResponse<MapArchiveRes> getMapArchives(double x, double y, int range){
        return new BaseResponse(archiveService.findMapArchive(x,y,range));
    }
    //아카이브 상세 조회
    @ResponseBody
    @GetMapping("/{archiveId}/detail")
    public BaseResponse<ArchiveDetailRes> getArchiveDetail(@PathVariable("archiveId")long id){
        return new BaseResponse<>(archiveService.findArchiveDetail(id));
    }
    //아카이브 삭제
    @ResponseBody
    @PatchMapping("/{archiveId}")
    public BaseResponse<BaseResponseStatus> changeArchiveStatus(@PathVariable("archiveId")long id){
        return new BaseResponse<>(archiveService.changeArchiveStatus(id));
    }

}
