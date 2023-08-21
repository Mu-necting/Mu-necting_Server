package com.munecting.server.domain.member.service;

import com.munecting.server.domain.member.DTO.get.MemberRankRes;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Transactional
class MemberServiceTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;
    @Test
    void findRankByMember() {
        for (long i=0;i<20;i++){
            //int num = (int) Math.random();
            Member member = new Member("member" + i,i);
            em.persist(member);
        }
        Member member11 = new Member("member", 19L);
        Member member20 = new Member("member", 20L);
        Member member15 = new Member("member", 15L);
        em.persist(member11);
        em.persist(member20);
        em.persist(member15);

        List<Object[]> rankMember = memberRepository.findRankByMember(10);
        List<MemberRankRes> result = rankMember.stream().map(
                rank -> new MemberRankRes((String) rank[0], (String) rank[1],
               (long) rank[2], (long) rank[3])
        ).toList();
        Assertions.assertThat(result.get(0).getRank()).isEqualTo(1);
    }
}