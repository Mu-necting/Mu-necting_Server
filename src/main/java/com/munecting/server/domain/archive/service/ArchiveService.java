package com.munecting.server.domain.archive.service;

import com.munecting.server.domain.archive.dto.get.ArchivePlusRes;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.domain.music.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ArchiveService {
    private final ArchiveRepository archiveRepository;
    private final MusicRepository musicRepository;
    private final MemberRepository memberRepository;
    //아카이브 상세 조회
    public ArchivePlusRes getArchivePlus(Long id){
        return archiveRepository.getArchivePlus(id);
    }
}
