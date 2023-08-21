package com.munecting.server.domain.archive.repository;
import com.munecting.server.domain.archive.entity.Archive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveRepository extends JpaRepository<Archive,Long>,ArchiveRepositoryCustom {
}
