package com.munecting.server.domain.archive.service;

import com.munecting.server.domain.archive.dto.get.ArchivePlusRes;
import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ArchiveService {
    private final ArchiveRepository archiveRepository;

    //아카이브 상세 조회
    public ArchivePlusRes getArchivePlus(Long id){
        return archiveRepository.getArchivePlus(id);
    }
    //주변에 있는 아카이브 조회
    public List<ArchiveRes> getArchive(double x,double y,int range){
        return archiveRepository.getArchive(x,y,range);
    }
}
