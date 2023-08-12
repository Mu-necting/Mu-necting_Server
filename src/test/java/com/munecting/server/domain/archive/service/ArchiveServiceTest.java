package com.munecting.server.domain.archive.service;

import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.music.dto.post.UploadMusicReq;
import com.munecting.server.domain.music.entity.Music;
import com.munecting.server.domain.music.entity.MusicGenre;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
@Rollback(value = false)
class ArchiveServiceTest {
    @Autowired
    ArchiveService archiveService;
    @Autowired
    ArchiveRepository archiveRepository;
    @PersistenceContext
    EntityManager em;
    @Test
    void saveArchive(){
        Member member1 = new Member("member1");
        em.persist(member1);
        UploadMusicReq uploadMusicReq = new UploadMusicReq("", "", "", "", "", MusicGenre.POP, LocalDateTime.now().plusHours(1)
                , 37.5393673, 126.9685016, 1);

        archiveService.saveArchive(uploadMusicReq,new Music());

        Archive result = archiveRepository.findById(Long.valueOf(1)).get();
        log.info("endTime = "+result.getEndTime());
        log.info("now = "+result.getCreateAt());

    }
}