package com.munecting.server.domain.reply.service;

import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.reply.entity.Reply;
import com.munecting.server.domain.reply.repository.ReplyRepository;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.domain.reply.dto.post.ReplyRequestDTO;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ArchiveRepository archiveRepository;
    private final MemberRepository memberRepository;

    public ReplyService(ReplyRepository replyRepository, ArchiveRepository archiveRepository, MemberRepository memberRepository) {
        this.replyRepository = replyRepository;
        this.archiveRepository = archiveRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void reply(Long archiveId, ReplyRequestDTO replyRequest) {
        Long memberId = replyRequest.getMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        Archive archive = archiveRepository.findArchiveById(archiveId);
        if (archive == null) {
            throw new DataRetrievalFailureException("Archive not found with id: " + archiveId);
        }

        if (replyRepository.existsByMemberIdAndArchiveId(member, archive)) {
            throw new IllegalArgumentException("Reply already exists.");
        }

        Reply reply = new Reply();
        reply.setMemberId(member);
        reply.setStatus("REPLIED");
        reply.setArchiveId(archive);

        replyRepository.save(reply);
        archive.increaseReplyCnt();
        archiveRepository.save(archive);

        updateReplyTotalCnt();
    }

    @Transactional
    public void updateReplyTotalCnt() {
        List<Member> allMembers = memberRepository.findAll();

        for (Member member : allMembers) {
            List<Archive> archivesWithSameMember = archiveRepository.findAllByMemberId(member.getId());

            int replyTotalCnt = archivesWithSameMember.stream()
                    .mapToInt(Archive::getReplyCnt)
                    .sum();

            member.setReplyTotalCnt(replyTotalCnt);
            memberRepository.save(member);
        }
    }

    @Transactional
    public int getReplyCount(Long archiveId) {
        Archive archive = archiveRepository.findArchiveById(archiveId);
        if (archive == null) {
            throw new DataRetrievalFailureException("Archive not found with id: " + archiveId);
        }
        return archive.getReplyCnt();
    }

    @Transactional
    public void unreply(Long archiveId, Long memberId) {
        Archive archive = archiveRepository.findArchiveById(archiveId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        Reply reply = replyRepository.findByMemberIdAndArchiveId(member, archive)
                .orElseThrow(() -> new DataRetrievalFailureException("Reply not found for member and archive"));

        replyRepository.delete(reply);

        archive.decreaseReplyCnt();
        archiveRepository.save(archive);

        updateReplyTotalCnt();
    }

    @Transactional
    public Set<String> getReplySendersForMember(Long memberId) {
        List<Long> archiveIdsForMember = archiveRepository.findArchiveIdsByMemberId(memberId);

        List<Long> senderMemberIds = replyRepository.findSenderMemberIdsByArchiveIds(archiveIdsForMember);

        List<String> senderNames = memberRepository.findNamesByMemberIds(senderMemberIds);

        return new HashSet<>(senderNames);
    }
}