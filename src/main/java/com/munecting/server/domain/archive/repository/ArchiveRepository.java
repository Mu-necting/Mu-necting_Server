package com.munecting.server.domain.archive.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface ArchiveRepository extends JpaRepository<Archive, Long>, ArchiveRepositoryCustom {
    Archive findArchiveById(Long archiveId);
    Optional<Member> findByEmail(String email);
    @Query("SELECT a FROM Archive a WHERE a.memberId.id = :memberId")
    List<Archive> findAllByMemberId(@Param("memberId") Long memberId);
    @Query("SELECT a.id FROM Archive a WHERE a.memberId.id = :memberId")
    List<Long> findArchiveIdsByMemberId(@Param("memberId") Long memberId);

}