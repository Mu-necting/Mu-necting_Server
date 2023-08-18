package com.munecting.server.domain.archive.repository;
import com.munecting.server.domain.archive.dto.get.ArchivePlusRes;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArchiveRepository {
    private final EntityManager em;

    // 아카이브 상세 페이지 조회
    public ArchivePlusRes getArchivePlus(Long id){
        return em.createQuery("select new com.munecting.server.domain.archive.dto.get.ArchivePlusRes(a.musicId.name, a.memberId.nickname,a.createAt,a.musicId.coverImg)" +
                        " from Archive a" +
                        " where a.id = :id ", ArchivePlusRes.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    // 음악 upload-archiveEntity
    public void postArchive(Archive archive){
        em.persist(archive);
    }

    public Archive findById(Long id) {
        return em.find(Archive.class, id);
    }

    public List<Archive> findAllByMemberId(Member memberId) {
        TypedQuery<Archive> query = em.createQuery(
                "SELECT a FROM Archive a WHERE a.memberId = :memberId",
                Archive.class);
        query.setParameter("memberId", memberId);
        return query.getResultList();
    }

}
