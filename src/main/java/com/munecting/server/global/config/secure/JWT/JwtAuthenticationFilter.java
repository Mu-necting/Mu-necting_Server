package com.munecting.server.global.config.secure.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
@Log4j2
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Header 에서 JWT 를 받아옴.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 유효한 token 인지 확인.
        if (token != null && jwtTokenProvider.validateToken(token)) {

            // Redis 에서 해당 accessToken 로그아웃 여부 확인
            String isLogout = (String) redisTemplate.opsForValue().get(token);

            // 로그아웃 되어 있지 않은 경우 해당 token 정상 작동.
            if (ObjectUtils.isEmpty(isLogout)) {
                // token 이 유효하면 해당 token 으로부터 user 정보를 받아옴.
                Authentication authentication = jwtTokenProvider.getAuthentication(token);

                // SecurityContext 에 Authentication 객체를 저장함.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}