package com.munecting.server.domain.archive.repository;

import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.dto.get.MapArchiveRes;
import com.munecting.server.domain.archive.dto.get.MyArchivePageRes;
import com.munecting.server.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArchiveRepositoryCustom {
    List<ArchiveRes> findNearArchive(double x, double y, int range);
    List<MapArchiveRes> findMapArchive(double x,double y,int range);
    MyArchivePageRes findArchiveByMember(Member member, Pageable pageable);
}
