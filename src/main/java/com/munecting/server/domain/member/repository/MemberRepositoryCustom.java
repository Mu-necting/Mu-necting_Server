package com.munecting.server.domain.member.repository;

import com.munecting.server.domain.member.DTO.get.MemberRankRes;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberRankRes> findRankByMember();
}
