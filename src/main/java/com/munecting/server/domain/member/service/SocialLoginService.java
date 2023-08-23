package com.munecting.server.domain.member.service;


import com.munecting.server.global.config.secure.JWT.JwtTokenProvider;
import com.munecting.server.global.config.secure.SocialConfig.RoleType;
import com.munecting.server.domain.member.DTO.TokenDTO;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocialLoginService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private RedisTemplate redisTemplate;
    public String socialLogin(String email, String name, String image) {
        Optional<Member> optionalUserEntity = memberRepository.findByEmail(email);
        if (optionalUserEntity.isPresent()) {

            Member userEntity = optionalUserEntity.get();
            userEntity.setLogin_at(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            userEntity.setLogin_cnt(userEntity.getLogin_cnt()+1);
            memberRepository.saveAndFlush(userEntity);

            // token 발급
            TokenDTO token = jwtTokenProvider.createAccessToken(email, "USER");

            // Redis 에 RTL user@email.com(key) : ----token-----(value) 형태로 token 저장
            redisTemplate.opsForValue().set("RT:"+email, token.getToken(), token.getTokenExpiresTime().getTime(), TimeUnit.MILLISECONDS);
            return token.getToken();
        }
        else {
            String password = UUID.randomUUID().toString();
            Member newUser = Member.builder()
                    .email(email)
                    .name(name)
                    .password(password)
                    .profileImage(image)
                    .replyTotalCnt(0L)
                    .status('A')
                    .role(String.valueOf(RoleType.USER))
                    .login_cnt(0L)
                    .build();
            return String.valueOf(memberRepository.saveAndFlush(newUser));
        }
    }
}
