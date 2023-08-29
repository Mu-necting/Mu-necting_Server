package com.munecting.server.domain.music.repository;

import com.munecting.server.domain.music.entity.Music;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music,Long> {
    
}
