package com.munecting.server.domain.pick.repository;

import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.domain.music.dto.post.UploadMusicReq;
import com.munecting.server.domain.music.entity.Music;
import com.munecting.server.domain.music.entity.MusicGenre;
import com.munecting.server.domain.music.service.MusicService;
import com.munecting.server.domain.pick.dto.get.PickDetailRes;
import com.munecting.server.domain.pick.dto.get.PicksPageRes;
import com.munecting.server.domain.pick.dto.get.PicksRes;
import com.munecting.server.domain.pick.entity.Pick;
import com.munecting.server.domain.pick.entity.QPick;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.munecting.server.domain.pick.entity.QPick.pick;
import static com.querydsl.core.types.ExpressionUtils.count;
import static org.assertj.core.api.Assertions.*;
import static org.hibernate.query.results.Builders.fetch;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class PickRepositoryTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    PickRepository pickRepository;
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
    void 픽_저장(){
        Member member1 = new Member("member1");
        em.persist(member1);
        Archive archive = new Archive(member1);
        em.persist(archive);

        Pick pick = pickRepository.save(new Pick("writing",member1,archive));

        Optional<Pick> result = pickRepository.findById(1L);
        assertThat(result.get()).isEqualTo(pick);
    }
    @Test
    void 내가한픽들조회(){
        Member member = em.find(Member.class, 1);
        Pageable pageable = PageRequest.of(1,2);
        List<PicksRes> picksRes = queryFactory
                .select(Projections.constructor(PicksRes.class,
                        pick.archiveId.musicId.coverImg,
                        pick.id))
                .from(pick)
                .where(pick.memberId.eq(member))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(pick.createAt.desc())
                .fetch();
        long totalCnt = queryFactory
                .select(pick.archiveId.musicId.coverImg.count())
                .from(pick)
                .where(pick.memberId.eq(member))
                .fetchOne();
        totalCnt = totalCnt/2+(totalCnt%2==0?0:1);
        PicksPageRes picksPageRes = new PicksPageRes(picksRes, totalCnt-1);
        log.info("picksPageRes : "+picksPageRes);
        log.info("totalCnt:"+totalCnt);
    }
    @Test
    void 픽상세조회(){
        long id = 1;
        Pick pick = em.find(Pick.class, id);

        PickDetailRes result = queryFactory
                .select(Projections.constructor(PickDetailRes.class,
                        QPick.pick.archiveId.musicId.name,
                        QPick.pick.archiveId.musicId.artist,
                        QPick.pick.createAt,
                        QPick.pick.archiveId.musicId.coverImg,
                        QPick.pick.writing,
                        QPick.pick.id))
                .from(QPick.pick)
                .where(QPick.pick.id.eq(id))
                .fetchOne();
        log.info("id",result);
    }
}