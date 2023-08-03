package com.munecting.server.domain.music.repository;

import com.munecting.server.domain.music.entity.Music;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MusicRepository {
    private final EntityManager em;
    //음악 upload-Music entity
    public void postMusic(Music music){
        em.persist(music);
    }
    public Music findByIdMusic(Long id){ return em.find(Music.class,id); }
}
