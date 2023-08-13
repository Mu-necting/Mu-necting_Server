package com.munecting.server.domain.archive.repository;

import com.munecting.server.domain.archive.dto.get.ArchiveRes;

import java.util.List;

public interface ArchiveRepositoryCustom {
    List<ArchiveRes> findNearArchive(double x, double y, int range);
}
