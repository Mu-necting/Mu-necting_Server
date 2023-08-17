package com.munecting.server.domain.archive.service;

import com.munecting.server.domain.archive.dto.get.ArchiveDetailRes;
import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.dto.get.MapArchiveRes;
import com.munecting.server.domain.archive.dto.get.MyArchivePageRes;
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
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ArchiveService {
    private final ArchiveRepository archiveRepository;
    private final MemberRepository memberRepository;

    //아카이브 저장
    @Transactional
    public void saveArchive(UploadMusicReq uploadMusicReq, Music music) {
        memberRepository.save(new Member("member1"));

        LocalDateTime endTime = LocalDateTime.now().plusHours(uploadMusicReq.getPlusTime());
        Optional<Member> findMember = memberRepository.findById((long) uploadMusicReq.getMemberId());
        archiveRepository.save(
                new Archive(findMember.get(),music,uploadMusicReq.getPointX(), uploadMusicReq.getPointY(),
                        endTime)
        );

    }
    //주변에 있는 아카이브 조회
    public List<ArchiveRes> findNearArchive(double x, double y, int range) {
        return archiveRepository.findNearArchive(x, y, range);
    }
    //내가 없로드한 아카이브 조회
    public MyArchivePageRes findArchiveByMember(long memberId, Pageable pageable){
        Optional<Member> findMember = memberRepository.findById(memberId);
        return archiveRepository.findArchiveByMember(findMember.get(),pageable);
    }
    // 맵 아카이브 조회
    public List<MapArchiveRes> findMapArchive(double x,double y,int range){
        return archiveRepository.findMapArchive(x,y,range);
    }
    // 아카이브 상세 조회
    public ArchiveDetailRes findArchiveDetail(long id){
        return archiveRepository.findArchiveDetailById(id);
    }
}
