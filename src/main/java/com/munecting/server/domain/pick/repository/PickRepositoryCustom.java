package com.munecting.server.domain.pick.repository;

import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.pick.dto.get.PickDetailRes;
import com.munecting.server.domain.pick.dto.get.PicksPageRes;
import com.munecting.server.domain.pick.dto.get.PicksRes;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PickRepositoryCustom {
    PicksPageRes findPicksByMember(Member member,Pageable pageable);
    PickDetailRes findPickDetail(long id);
}
