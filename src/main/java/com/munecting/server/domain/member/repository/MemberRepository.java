package com.munecting.server.domain.member.repository;

import com.munecting.server.domain.member.DTO.MemberDTO;
import com.munecting.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>,MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);
}
