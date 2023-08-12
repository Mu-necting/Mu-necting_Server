package com.munecting.server.domain.archive.repository;

import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.member.entity.Member;
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
    @BeforeEach
    void makeTest(){
        Member member3 = new Member("member3");
        em.persist(member3);
        Archive archiveIn = new Archive(member3, 37.5393673,126.9685016, LocalDateTime.now().plusHours(1)); //out
        Archive archiveIn1 = new Archive(member3, 37.5394141,126.965519, LocalDateTime.now().plusHours(1)); //in
        Archive archive2 = new Archive(member3, 37.5393737,126.9648216, LocalDateTime.now().plusHours(1)); //in
        Archive timeOut = new Archive(member3, 37.5393737,126.9648216, LocalDateTime.now()); //in
        em.persist(archiveIn);
        em.persist(archiveIn1);
        em.persist(archive2);
        em.persist(timeOut);
    }
    @Test
    void findNearArchive() {
        List<ArchiveRes> result = archiveRepository.findNearArchive(37.5393737,126.9648216, 1000);
        for(ArchiveRes res : result){
            log.info("nearArchive : "+res);
        }
    }
}