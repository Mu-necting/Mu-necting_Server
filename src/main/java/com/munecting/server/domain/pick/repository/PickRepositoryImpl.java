package com.munecting.server.domain.pick.repository;

import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.pick.dto.get.PicksPageRes;
import com.munecting.server.domain.pick.dto.get.PicksRes;
import com.munecting.server.domain.pick.entity.QPick;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.munecting.server.domain.pick.entity.QPick.*;
import static com.querydsl.core.types.ExpressionUtils.count;

public class PickRepositoryImpl implements PickRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public PickRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public PicksPageRes findPicksByMember(Member member, Pageable pageable){
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

}
