package com.munecting.server.domain.archive.repository;

import com.munecting.server.domain.archive.dto.get.ArchivePlusRes;
import com.munecting.server.domain.archive.dto.get.ArchiveRes;
import com.munecting.server.domain.archive.entity.Archive;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArchiveRepository {
    private final EntityManager em;
    //아카이브 상세 페이지 조회
    public ArchivePlusRes getArchivePlus(Long id){
        return em.createQuery("select new com.munecting.server.domain.archive.dto.get.ArchivePlusRes(a.musicId.name, a.memberId.nickname,a.createAt,a.musicId.coverImg)" +
                        " from Archive a" +
                        " where a.id = :id ", ArchivePlusRes.class)
                .setParameter("id",id)
                .getSingleResult();
    }
    //음악 upload-archiveEntity
    public void postArchive(Archive archive){
        em.persist(archive);
    }
    //주변 아카이브 조회
    public List<ArchiveRes> getArchive(double x,double y,int range){
        return em.createQuery("SELECT new com.munecting.server.domain.archive.dto.get.ArchiveRes(a.musicId.name,a.musicId.coverImg,a.musicId.genre,a.musicId.musicPre,a.musicId.musicPull,a.replyCnt,a.id) " +
                " FROM Archive a " +
                "where ST_Distance_Sphere(Point(:y,:x),Point(a.pointY,a.pointX)) <= :range ",ArchiveRes.class)
                .setParameter("x",x)
                .setParameter("y",y)
                .setParameter("range",range)
                .getResultList();
    }

}
