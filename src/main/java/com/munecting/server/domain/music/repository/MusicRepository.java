package com.munecting.server.domain.music.repository;

import com.munecting.server.domain.music.entity.Music;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MusicRepository {
    private final EntityManager em;
    //음악 upload-Music entity
    public void postMusic(Music music){
        em.persist(music);
    }
    public Music findByIdMusic(Long id){ return em.find(Music.class,id); }

    public Music findById(Long id) {
        return em.createQuery("SELECT m FROM Music m WHERE m.id = :id", Music.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Long findMusicIdByName(String musicName) {
        return em.createQuery("SELECT m.id FROM Music m WHERE m.name = :musicName", Long.class)
                .setParameter("musicName", musicName)
                .getSingleResult();
    }

}
