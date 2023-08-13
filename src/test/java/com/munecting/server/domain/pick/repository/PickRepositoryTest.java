package com.munecting.server.domain.pick.repository;

import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.domain.music.dto.post.UploadMusicReq;
import com.munecting.server.domain.music.entity.MusicGenre;
import com.munecting.server.domain.music.service.MusicService;
import com.munecting.server.domain.pick.entity.Pick;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class PickRepositoryTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    PickRepository pickRepository;
    @Test
    void 픽_저장(){
        Member member1 = new Member("member1");
        em.persist(member1);
        Archive archive = new Archive(member1);
        em.persist(archive);

        Pick pick = pickRepository.save(new Pick("writing",member1,archive));

        Optional<Pick> result = pickRepository.findById(1L);
        Assertions.assertThat(result.get()).isEqualTo(pick);
    }
}