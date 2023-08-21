package com.munecting.server.domain.member.repository;

import com.munecting.server.domain.member.DTO.get.MemberRankRes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepositoryCustom {
    @Query(value = "SELECT * FROM (" +
        "SELECT m.profile_image as image , m.name as name , m.all_reply_cnt, DENSE_RANK() OVER (ORDER BY m.all_reply_cnt DESC) AS ra " +
        "FROM member m) AS sub " +
        "WHERE sub.ra <= :rank", nativeQuery = true)
    List<Object[]> findRankByMember(@Param("rank") int rank);
}
