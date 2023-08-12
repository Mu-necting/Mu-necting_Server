package com.munecting.server.domain.archive.repository;

import com.munecting.server.domain.archive.dto.get.ArchivePlusRes;
import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.entity.Archive;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ArchiveRepository extends JpaRepository<Archive,Long>,ArchiveRepositoryCustom {
}
