package com.munecting.server.domain.pick.controller;

import com.munecting.server.domain.pick.dto.post.PickReq;
import com.munecting.server.domain.pick.repository.PickRepository;
import com.munecting.server.domain.pick.service.PickService;
import com.munecting.server.global.config.BaseResponse;
import com.munecting.server.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pick")
@RequiredArgsConstructor
public class PickController {
    private final PickService pickService;
    @ResponseBody
    @PostMapping("")
    public BaseResponse<BaseResponseStatus> postPick(@RequestBody PickReq pickReq){
        pickService.savePick(pickReq);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
