package com.munecting.server.domain.archive.service;

import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.domain.music.dto.post.UploadMusicReq;
import com.munecting.server.domain.music.entity.Music;
import com.munecting.server.domain.music.repository.MusicRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArchiveService {
    private final ArchiveRepository archiveRepository;
    private final MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;
    //아카이브 저장
    public void saveArchive(UploadMusicReq uploadMusicReq, Music music) {
        Member member1 = new Member("member1");
        em.persist(member1);

        Optional<Member> findMember = memberRepository.findById(Long.valueOf(uploadMusicReq.getMemberId()));
        archiveRepository.save(
                new Archive(findMember.get(),music,uploadMusicReq.getPointX(), uploadMusicReq.getPointY(),
                        uploadMusicReq.getEndTime())
        );

    }
    @Transactional(readOnly = true)
    //주변에 있는 아카이브 조회
    public List<ArchiveRes> findNearArchive(double x, double y, int range) {
        return archiveRepository.findNearArchive(x, y, range);
    }
}
