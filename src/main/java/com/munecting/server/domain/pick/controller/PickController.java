package com.munecting.server.domain.pick.controller;

import com.munecting.server.domain.pick.dto.get.PicksPageRes;
import com.munecting.server.domain.pick.dto.get.PicksRes;
import com.munecting.server.domain.pick.dto.post.PickReq;
import com.munecting.server.domain.pick.service.PickService;
import com.munecting.server.global.config.BaseResponse;
import com.munecting.server.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pick")
@RequiredArgsConstructor
public class PickController {
    private final PickService pickService;
    //픽저장
    @ResponseBody
    @PostMapping("")
    public BaseResponse<BaseResponseStatus> postPick(@RequestBody PickReq pickReq){
        pickService.savePick(pickReq);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
    //내가 픽한 픽 다건 조회 (최신순,인기순)
    @ResponseBody
    @GetMapping("/{memberId}/")
    public BaseResponse<PicksPageRes> getPicks(@PathVariable("memberId")long memberId,
                                               @PageableDefault(page=0, size=2)Pageable pageable){
        return new BaseResponse<>(pickService.findPicks(memberId,pageable));
    }
}
