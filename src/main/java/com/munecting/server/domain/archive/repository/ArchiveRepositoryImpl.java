package com.munecting.server.domain.archive.repository;

import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class ArchiveRepositoryImpl implements ArchiveRepositoryCustom{
    private final EntityManager em;

    //주변 아카이브
    @Override
    public List<ArchiveRes> findNearArchive(double x,double y,int range){
        return em.createQuery("SELECT new com.munecting.server.domain.archive.dto.get.ArchiveRes(a.musicId.name,a.musicId.coverImg,a.musicId.genre,a.musicId.musicPre,a.musicId.musicPull,a.replyCnt,a.id," +
                        "a.musicId.artist) " +
                        " FROM Archive a " +
                        "where ST_Distance_Sphere(Point(:y,:x),Point(a.pointY,a.pointX)) <= :range " +
                        "and a.endTime > now()",ArchiveRes.class)
                .setParameter("x",x)
                .setParameter("y",y)
                .setParameter("range",range)
                .getResultList();
    }
}
