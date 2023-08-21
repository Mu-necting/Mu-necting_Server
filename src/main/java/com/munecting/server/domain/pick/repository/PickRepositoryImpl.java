package com.munecting.server.domain.pick.repository;

import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.pick.dto.get.PickDetailRes;
import com.munecting.server.domain.pick.dto.get.PicksPageRes;
import com.munecting.server.domain.pick.dto.get.PicksRes;
import com.munecting.server.domain.pick.entity.QPick;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.munecting.server.domain.pick.entity.QPick.*;
@RequiredArgsConstructor
public class PickRepositoryImpl implements PickRepositoryCustom{
    private final EntityManager em;
    //내가 픽한 픽 조회-최신순
    @Override
    public PicksPageRes findPicksByMember(Member member, Pageable pageable){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
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
        return new PicksPageRes(picksRes,totalCnt-1);
    }
    //픽 상세 조회
    @Override
    public PickDetailRes findPickDetail(long id){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        return queryFactory
                .select(Projections.constructor(PickDetailRes.class,
                        pick.archiveId.musicId.name,
                        pick.archiveId.musicId.artist,
                        pick.createAt,
                        pick.archiveId.musicId.coverImg,
                        pick.writing))
                .from(pick)
                .where(pick.id.eq(id))
                .fetchOne();
    }
}
