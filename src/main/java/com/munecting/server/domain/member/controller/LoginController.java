package com.munecting.server.domain.member.controller;

import com.munecting.server.domain.member.DTO.MemberDTO;
import com.munecting.server.domain.member.DTO.TokenDTO;
import com.munecting.server.domain.member.DTO.VerifyDTO;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.service.LoginService;
import com.munecting.server.global.config.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "")
public class LoginController {

    @Autowired
    private LoginService loginService;


    // 회원가입
    @PostMapping("/join")
    public BaseResponse<Member> join(@RequestBody MemberDTO user) throws Exception {

        return new BaseResponse<>(loginService.join(user));
    }

    // 이메일 인증
    @PostMapping("/mailCheck")
    public BaseResponse<String> mailCheck(@RequestBody MemberDTO user) throws Exception {
        String result = loginService.mailCheck(user);
        return new BaseResponse<>(result);
    }

    // 이메일 인증 검증
    @PostMapping("/mailCheck/verify")
    public BaseResponse<String> verifyEmail(@RequestBody VerifyDTO verify) throws Exception {
        boolean isValid = loginService.verifyEmailCode(verify.getEmail(), verify.getCode());
        if (isValid) {
            return new BaseResponse<>("인증이 완료되었습니다.");
        } else {
            return new BaseResponse<>("인증 번호가 틀립니다.");
        }
    }

    // 로그인
    @PostMapping("/login")
    public BaseResponse<List<TokenDTO>> login(@RequestBody MemberDTO user) throws Exception {
        List<TokenDTO> tokens = loginService.login(user);
        return new BaseResponse<>(tokens);
    }


    // 로그아웃
    @PostMapping("/log-out")
    public BaseResponse<String> logout(HttpServletRequest request) throws Exception {
        String result = loginService.logout(request);
        return new BaseResponse<>(result);
    }

    // 비밀번호 찾기
    @PostMapping("/findpw")
    public BaseResponse<String> findPw(@RequestBody MemberDTO user) throws Exception {
        String result = loginService.findPw(user);
        return new BaseResponse<>(result);
    }

    // Access 토큰 재발급 api
    @PostMapping("/accesstoken")
    public BaseResponse<String> getNewAccessToken(@RequestBody MemberDTO user, HttpServletRequest request) throws Exception {
        String accessToken = loginService.getNewAccessToken(user.getEmail(), request);
        return new BaseResponse<>(accessToken);
    }
}