package com.munecting.server.domain.pick.controller;

import com.munecting.server.domain.pick.dto.get.PickDetailRes;
import com.munecting.server.domain.pick.dto.get.PicksPageRes;
import com.munecting.server.domain.pick.dto.post.PickReq;
import com.munecting.server.domain.pick.service.PickService;
import com.munecting.server.global.config.BaseResponse;
import com.munecting.server.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    //픽 상세 조회
    @ResponseBody
    @GetMapping("/{pickId}/detail")
    public BaseResponse<PickDetailRes> getPickDetail(@PathVariable("pickId")long pickId){
        return new BaseResponse<>(pickService.findPick(pickId));
    }
}
