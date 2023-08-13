package com.munecting.server.domain.member.repository;

import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.pick.dto.post.PickReq;
import com.munecting.server.domain.pick.entity.Pick;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Transactional
class MemberRepositoryTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    private MemberRepository memberRepository;
    @Test
    void testJPA(){
        Member m = new Member("m1");

        memberRepository.save(m);
        Optional<Member> result = memberRepository.findById(1L);
        log.info("member = "+result.get());
    }
    @Test
    void find픽DTO_id(){
        Member member1 = new Member("member1");
        em.persist(member1);
        PickReq pickReq = new PickReq("fghjk", 1, 1);

        Optional<Member> findMember = memberRepository.findById(pickReq.getMemberId());

        assertThat(member1).isEqualTo(findMember.get());
    }

}