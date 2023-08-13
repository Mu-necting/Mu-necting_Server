package com.munecting.server.domain.pick.repository;

import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.pick.entity.Pick;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickRepository extends JpaRepository<Pick,Long>,PickRepositoryCustom{
}
