package com.munecting.server.domain.archive.repository;

import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.dto.get.MyArchivePageRes;
import com.munecting.server.domain.archive.dto.get.MyArchivesRes;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.service.ArchiveService;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.music.dto.post.UploadMusicReq;
import com.munecting.server.domain.music.entity.Music;
import com.munecting.server.domain.music.entity.MusicGenre;
import com.munecting.server.domain.music.service.MusicService;
import com.munecting.server.domain.pick.dto.get.PicksPageRes;
import com.munecting.server.domain.pick.entity.Pick;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.munecting.server.domain.archive.entity.QArchive.archive;
import static com.munecting.server.domain.pick.entity.QPick.pick;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class ArchiveRepositoryImplTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    ArchiveRepository archiveRepository;
    @Autowired
    ArchiveService archiveService;
    @Autowired
    MusicService musicService;
    JPAQueryFactory queryFactory;

    @BeforeEach
    void testValue(){
        queryFactory = new JPAQueryFactory(em);
        for (int i=1;i<=10;i++){
            Member member = new Member("member"+i);
            em.persist(member);
            for(int j=1;j<=4;j++){
                Music music = new Music("coverImg"+j);
                em.persist(music);
                Archive archive = new Archive(member,music);
                em.persist(archive);
                Pick pick = new Pick("", member, archive);
                em.persist(pick);

            }
        }
    }
    @Test
    void findNearArchive() {
        List<ArchiveRes> result = archiveRepository.findNearArchive(37.5393737,126.9648216, 100);
        for(ArchiveRes res : result){
            log.info("nearArchive : "+res);
        }
    }
    @Test
    void findArchiveByMember(){

        Member member = em.find(Member.class, 1);
        Pageable pageable = PageRequest.of(0,2);

        List<MyArchivesRes> myArchivesRes = queryFactory
                .select(Projections.constructor(MyArchivesRes.class,
                        archive.musicId.coverImg,
                        archive.id))
                .from(archive)
                .where(archive.memberId.eq(member))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(archive.createAt.desc())
                .fetch();
        long totalCnt = queryFactory
                .select(archive.count())
                .from(archive)
                .where(archive.memberId.eq(member))
                .fetchOne();
        totalCnt = totalCnt/2+(totalCnt%2==0?0:1);

        MyArchivePageRes myArchivePageRes = new MyArchivePageRes(myArchivesRes, totalCnt - 1);
        log.info("picksPageRes : "+myArchivePageRes);
        log.info("totalCnt:"+totalCnt);
    }
}