package com.munecting.server.domain.pick.service;

import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.domain.pick.dto.post.PickReq;
import com.munecting.server.domain.pick.entity.Pick;
import com.munecting.server.domain.pick.repository.PickRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class PickServiceTest {
    @Autowired
    PickRepository pickRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ArchiveRepository archiveRepository;
    @PersistenceContext
    EntityManager em;
    @Test
    void savePick() {
        Member member1 = new Member("member1");
        em.persist(member1);
        Archive archive = new Archive(member1);
        em.persist(archive);

        PickReq pickReq = new PickReq("", 1, 1);
        Member findMember = memberRepository.findById(pickReq.getMemberId()).get();
        Optional<Archive> findArchive = archiveRepository.findById(pickReq.getArchiveId());
        Pick pick = new Pick(pickReq.getWriting(), findMember, findArchive.get());
        Pick save = pickRepository.save(pick);

        Assertions.assertThat(save).isEqualTo(pick);
    }
}