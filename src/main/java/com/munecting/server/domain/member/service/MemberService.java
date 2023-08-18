package com.munecting.server.domain.member.service;

import com.munecting.server.global.config.secure.JWT.JwtTokenProvider;
import com.munecting.server.domain.member.DTO.MemberDTO;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RedisTemplate redisTemplate;


    public Optional<Member> getMyInfo(HttpServletRequest request) throws Exception {
        return memberRepository.findByEmail(jwtTokenProvider.getCurrentUser(request));
    }

    public Optional<Member> updateMyInfo(MemberDTO user, HttpServletRequest request) throws Exception {
        String email = jwtTokenProvider.getCurrentUser(request);
        Member userEntity = memberRepository.findByEmail(email).orElse(null);
        if (userEntity == null) {
            throw new Exception("사용자를 찾을 수 없습니다.");
        }
        if (user.getName() != null) {
            userEntity.setName(user.getName());
        }
        if (user.getProfileImage() != null) {
            userEntity.setProfileImage(user.getProfileImage());
        }

        return Optional.of(memberRepository.saveAndFlush(userEntity));

    }

    public Optional<Member> deactivateUser(HttpServletRequest request) throws Exception {
        String email = jwtTokenProvider.getCurrentUser(request);
        Member userEntity = memberRepository.findByEmail(email).orElse(null);
        if (userEntity == null) {
            throw new Exception("사용자를 찾을 수 없습니다.");
        }

        // Redis 에서 로그인되어 있는 토큰 삭제
        Object token = redisTemplate.opsForValue().get("RT:" + email);
        if (token != null) {
            redisTemplate.delete("RT:"+email);
        }
        // 탈퇴한 토큰 차단
        Long expire = jwtTokenProvider.getExpireTime((String) token).getTime();
        redisTemplate.opsForValue().set(token, "logout", expire, TimeUnit.MILLISECONDS);

        userEntity.setStatus('D');
        return Optional.of(memberRepository.saveAndFlush(userEntity));
    }
}