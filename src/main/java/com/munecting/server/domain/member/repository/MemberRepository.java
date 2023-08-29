package com.munecting.server.domain.member.repository;

import com.munecting.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>,MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);
    @Query("SELECT m.name FROM Member m WHERE m.id IN :memberIds")
    List<String> findNamesByMemberIds(@Param("memberIds") List<Long> memberIds);
}
