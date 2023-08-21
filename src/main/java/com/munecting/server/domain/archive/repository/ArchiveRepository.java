package com.munecting.server.domain.archive.repository;
import com.munecting.server.domain.archive.entity.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import com.munecting.server.domain.member.entity.Member;

import java.util.List;

public interface ArchiveRepository extends JpaRepository<Archive,Long>,ArchiveRepositoryCustom {
    Archive findArchiveById(Long archiveId);
    List<Archive> findAllByMemberId(Member member);
}
