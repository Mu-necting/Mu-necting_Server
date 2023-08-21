package com.munecting.server.domain.member.repository;

import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.member.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;
    //멤버 Id로 찾기
    public Member findByIdMember(Long id){ return em.find(Member.class,id); }

    public void postMember(Member member){
        em.persist(member);
    }
}

