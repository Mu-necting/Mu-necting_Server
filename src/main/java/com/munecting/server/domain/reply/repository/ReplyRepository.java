package com.munecting.server.domain.reply.repository;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    boolean existsByMemberIdAndArchiveId(Member member, Archive archive);
    void deleteByMemberIdAndArchiveId(Member member, Archive archive);
    int countByMemberId(Member member);
    Optional<Reply> findByMemberIdAndArchiveId(Member member, Archive archive);

    @Query("SELECT DISTINCT r.memberId.id FROM Reply r WHERE r.archiveId.id IN :archiveIds")
    List<Long> findSenderMemberIdsByArchiveIds(@Param("archiveIds") List<Long> archiveIds);

}
