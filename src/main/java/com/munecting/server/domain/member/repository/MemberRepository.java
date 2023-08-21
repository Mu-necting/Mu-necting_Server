package com.munecting.server.domain.member.repository;

import com.munecting.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByOrderByReplyTotalCntDesc();
}

/*
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
}
*/
