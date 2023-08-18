package com.munecting.server.domain.music.service;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.reply.entity.Reply;
import com.munecting.server.domain.music.repository.ReplyRepository;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.domain.music.dto.post.ReplyRequestDTO;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ArchiveRepository archiveRepository;
    private final MemberRepository memberRepository;

    public ReplyService(ReplyRepository replyRepository, ArchiveRepository archiveRepository,MemberRepository memberRepository) {
        this.replyRepository = replyRepository;
        this.archiveRepository = archiveRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void reply(Long archiveId, ReplyRequestDTO replyRequest) {
        Long memberId = replyRequest.getMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new DataRetrievalFailureException("Member not found with id: " + memberId));
        Archive archive = archiveRepository.findById(archiveId);

        //중복 방지
        if (replyRepository.existsByMemberIdAndArchiveId(member, archive)) {
            throw new IllegalArgumentException("Reply already exists.");
        }

        Reply reply = new Reply();
        reply.setMemberId(member);
        reply.setStatus("REPLIED");
        reply.setArchiveId(archive);

        // Reply 저장
        replyRepository.save(reply);
        archive.increaseReplyCnt(); // replyCnt 증가
        archiveRepository.postArchive(archive);

        updateReplyTotalCnt(member.getId());
    }

    @Transactional
    public void updateReplyTotalCnt(Long archiveId) {
        Archive archive = archiveRepository.findById(archiveId);

        Long memberId = archive.getMemberId().getId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new DataRetrievalFailureException("Member not found with id: " + memberId));

        List<Archive> archivesWithSameMember = archiveRepository.findAllByMemberId(member);
        int replyTotalCnt = archivesWithSameMember.stream()
                .mapToInt(Archive::getReplyCnt)
                .sum();

        member.setReplyTotalCnt(replyTotalCnt);
        memberRepository.save(member);
    }

//reply 개수 조회
@Transactional
    public int getReplyCount(Long archiveId) {
        Archive archive = archiveRepository.findById(archiveId);
        if (archive == null) {
            throw new DataRetrievalFailureException("Archive not found with id: " + archiveId);
        }
        return archive.getReplyCnt();
    }



}


