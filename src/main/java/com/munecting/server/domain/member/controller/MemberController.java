package com.munecting.server.domain.member.controller;

import com.munecting.server.domain.member.service.MemberService;
import com.munecting.server.global.config.BaseResponse;
import com.munecting.server.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    //프로필 생성
    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<BaseResponseStatus> joinMember(@RequestPart("profile") MultipartFile profile) throws IOException {
        memberService.joinMember(profile);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
