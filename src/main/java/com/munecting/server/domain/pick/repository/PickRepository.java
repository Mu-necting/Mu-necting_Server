package com.munecting.server.domain.pick.repository;

import com.munecting.server.domain.pick.entity.Pick;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickRepository extends JpaRepository<Pick,Long> {
}
