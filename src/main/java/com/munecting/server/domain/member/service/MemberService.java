package com.munecting.server.domain.member.service;

import com.munecting.server.global.config.secure.JWT.JwtTokenProvider;
import com.munecting.server.domain.member.DTO.MemberDTO;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.global.utils.S3Storage.S3Uploader;
import com.munecting.server.global.utils.S3Storage.UploadImageS3;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private UploadImageS3 uploadImageS3;


    public Optional<Member> getMyInfo(HttpServletRequest request) throws Exception {
        return memberRepository.findByEmail(jwtTokenProvider.getCurrentUser(request));
    }

    public Optional<Member> updateMyInfo(MemberDTO user, HttpServletRequest request,
                                         MultipartFile profile) throws Exception {
        String saveFilePath = null;
        if(!profile.isEmpty()) {  // 기본 프로필 아니면
            String fileName = "image" + File.separator + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));

            // 저장할 새 이름
            long time = System.currentTimeMillis();
            String originalFilename = profile.getOriginalFilename();
            String saveFileName = String.format("%d_%s", time, originalFilename.replaceAll(" ", ""));

            // 이미지 업로드 : 이걸 DB에 저장하면 됩니다!
            saveFilePath = uploadImageS3.upload(profile, fileName, saveFileName);
        }

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