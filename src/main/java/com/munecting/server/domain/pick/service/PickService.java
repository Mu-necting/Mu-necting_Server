package com.munecting.server.domain.pick.service;

import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.domain.pick.dto.post.PickReq;
import com.munecting.server.domain.pick.entity.Pick;
import com.munecting.server.domain.pick.repository.PickRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PickService {
    private final PickRepository pickRepository;
    private final MemberRepository memberRepository;
    private final ArchiveRepository archiveRepository;
    @Transactional
    public void savePick(PickReq pickReq){
        Optional<Member> findMember = memberRepository.findById(pickReq.getMemberId());
        Optional<Archive> findArchive = archiveRepository.findById(pickReq.getArchiveId());
        pickRepository.save(new Pick(pickReq.getWriting(),findMember.get(),findArchive.get()));
    }
}
