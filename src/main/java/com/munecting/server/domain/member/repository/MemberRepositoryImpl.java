package com.munecting.server.domain.member.repository;

import com.munecting.server.domain.member.DTO.get.MemberRankRes;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.munecting.server.domain.member.entity.QMember.*;


@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final EntityManager em;

    @Override
    public List<MemberRankRes> findRankByMember(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        int rank = 10;

        return queryFactory
                .select(
                        Projections.constructor(MemberRankRes.class,
                        member.profileImage,
                        member.name,
                        member.allReplyCnt))
                .from(member)
                .where(member.status.eq('A'))
                .orderBy(member.allReplyCnt.desc())
                .limit(rank)
                .fetch();
    }

}
