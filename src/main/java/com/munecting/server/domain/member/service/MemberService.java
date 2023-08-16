package com.munecting.server.domain.member.service;

import com.amazonaws.services.s3.AmazonS3;
import com.munecting.server.ServerApplication;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.global.utils.S3Storage.S3Uploader;
import com.munecting.server.global.utils.S3Storage.UploadImageS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final UploadImageS3 uploadImageS3;
    //프로필 사진
    public void joinMember(MultipartFile profile) throws IOException {
        String saveFilePath = null;
        if(!profile.isEmpty()) {  // 기본 프로필 아니면
            String fileName = "image" + File.separator + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));

            // 저장할 새 이름
            long time = System.currentTimeMillis();
            String originalFilename = profile.getOriginalFilename();
            String saveFileName = String.format("%d_%s", time, originalFilename.replaceAll(" ", ""));

            // 이미지 업로드
            saveFilePath = uploadImageS3.upload(profile, fileName, saveFileName);
        }
    }
}
