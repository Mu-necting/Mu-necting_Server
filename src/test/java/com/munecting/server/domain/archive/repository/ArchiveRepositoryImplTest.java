package com.munecting.server.domain.archive.repository;

import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.service.ArchiveService;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.music.dto.post.UploadMusicReq;
import com.munecting.server.domain.music.entity.MusicGenre;
import com.munecting.server.domain.music.service.MusicService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class ArchiveRepositoryImplTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    ArchiveRepository archiveRepository;
    @Autowired
    ArchiveService archiveService;
    @Autowired
    MusicService musicService;

    @BeforeEach
    void makeArchive(){
        Member member3 = new Member("member3");
        em.persist(member3);
        UploadMusicReq marker1 = new UploadMusicReq("", "", "", "", "", MusicGenre.POP, 1
                , 37.5393673, 126.9685016, 1);
        UploadMusicReq marker2 = new UploadMusicReq("", "", "", "", "", MusicGenre.POP, 2
                , 37.5394141,126.965519, 1);
        UploadMusicReq marker3 = new UploadMusicReq("", "", "", "", "", MusicGenre.POP, 1
                , 37.5393737,126.9648216, 1);
        UploadMusicReq marker4 = new UploadMusicReq("", "", "", "", "", MusicGenre.POP, 0
                , 37.5393737,126.9648216, 1);

        archiveService.saveArchive(marker1,musicService.saveMusic(marker1));
        archiveService.saveArchive(marker2,musicService.saveMusic(marker2));
        archiveService.saveArchive(marker3,musicService.saveMusic(marker3));
        archiveService.saveArchive(marker4,musicService.saveMusic(marker4));
    }
    @Test
    void findNearArchive() {
        List<ArchiveRes> result = archiveRepository.findNearArchive(37.5393737,126.9648216, 100);
        for(ArchiveRes res : result){
            log.info("nearArchive : "+res);
        }
    }
}