package com.munecting.server.domain.member.repository;

import com.munecting.server.domain.member.DTO.get.MemberRankRes;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.pick.dto.post.PickReq;
import com.munecting.server.domain.pick.entity.Pick;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.CollectionAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.munecting.server.domain.member.entity.QMember.member;
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
    @Test
    void 뮤넥터순위조회(){
        for (long i=0;i<20;i++){
            //int num = (int) Math.random();
            Member member = new Member("member" + i,i);
            em.persist(member);
        }
        Member member11 = new Member("member", 19L);
        Member member191 = new Member("member", 19L);
        Member member15 = new Member("member", 15L);
        em.persist(member11);
        em.persist(member191);
        em.persist(member15);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        int rank = 10;

        List<MemberRankRes> result = queryFactory
                .select(
                        Projections.constructor(MemberRankRes.class,
                                member.profileImage,
                                member.name,
                                member.all_replyCnt))
                .from(member)
               // .where(member.status.eq('A'))
                .orderBy(member.all_replyCnt.desc())
                .limit(rank)
                .fetch();
        assertThat(result.get(0).getAllReplyCnt()).isEqualTo(19);
        log.info("res = "+result);
    }

}