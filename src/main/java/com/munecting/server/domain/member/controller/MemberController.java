package com.munecting.server.domain.member.controller;

import com.munecting.server.domain.member.DTO.ChangePwInfo;
import com.munecting.server.domain.member.DTO.MemberDTO;
import com.munecting.server.domain.member.DTO.get.MemberRankRes;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.service.LoginService;
import com.munecting.server.domain.member.service.MemberService;
import com.munecting.server.global.config.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private LoginService loginService;


    // 유저 정보 조회
    @GetMapping("")
    public BaseResponse<Optional<Member>> getMyInfo(HttpServletRequest request) throws Exception {
        Optional<Member> user = memberService.getMyInfo(request);
        return new BaseResponse<>(user);
    }

    // 유저 정보 수정
    @PostMapping("")
    public BaseResponse<Optional<Member>> updateMyInfo(@RequestPart("member") MemberDTO user, HttpServletRequest request,
                                             @RequestPart("profile") MultipartFile profile) throws Exception {
        memberService.updateMyInfo(user, request,profile);
        Optional<Member> member = memberService.getMyInfo(request);
        return new BaseResponse<>(member);
    }

    // 유저 비밀번호 확인
    @PostMapping("/validatepw")
    public BaseResponse<String> validatePw(@RequestBody Map<String, String> requestBody, HttpServletRequest request) throws Exception {
        if (loginService.validatePw(requestBody.get("originalPw"), request))
            return new BaseResponse<>("비밀번호 확인");
        return new BaseResponse<>("비밀번호 불일치");
    }

    // 유저 비밀번호 변경
    @PostMapping("/changepw")
    public BaseResponse<String> updatePw(@RequestBody ChangePwInfo changePw, HttpServletRequest request) throws Exception {
        loginService.changePw(changePw, request);
        return new BaseResponse<>("비밀번호가 수정되었습니다.");
    }

    // 유저 회원 탈퇴( A -> D )
    @PostMapping("/delete")
    public BaseResponse<Optional<Member>> deactivateUser(HttpServletRequest request) throws Exception {
        Optional<Member> userEntity = memberService.deactivateUser(request);
        return new BaseResponse<>(userEntity);
    }
    //뮤넥터 랭킹 조회
    @GetMapping("/rank")
    public BaseResponse<List<MemberRankRes>> getRank(int rank){
        return new BaseResponse<>(memberService.findRankByMember(rank));
    }

}