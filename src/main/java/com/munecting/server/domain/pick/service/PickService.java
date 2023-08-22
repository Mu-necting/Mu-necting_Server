package com.munecting.server.domain.pick.service;

import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.domain.pick.dto.get.PickDetailRes;
import com.munecting.server.domain.pick.dto.get.PicksPageRes;
import com.munecting.server.domain.pick.dto.get.PicksRes;
import com.munecting.server.domain.pick.dto.patch.PickChangeReq;
import com.munecting.server.domain.pick.dto.post.PickReq;
import com.munecting.server.domain.pick.entity.Pick;
import com.munecting.server.domain.pick.repository.PickRepository;
import com.munecting.server.global.config.BaseResponse;
import com.munecting.server.global.config.BaseResponseStatus;
import com.munecting.server.global.config.secure.JWT.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PickService {
    private final PickRepository pickRepository;
    private final MemberRepository memberRepository;
    private final ArchiveRepository archiveRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    //픽 저장
    @Transactional
    public void savePick(PickReq pickReq, HttpServletRequest memberId)throws Exception{
        Optional<Member> findMember = memberRepository.findByEmail(jwtTokenProvider.getCurrentUser(memberId));
        Optional<Archive> findArchive = archiveRepository.findById(pickReq.getArchiveId());
        findArchive.get().setPickCnt();
        pickRepository.save(new Pick(pickReq.getWriting(),findMember.get(),findArchive.get()));
    }
    //내가한 픽s 조회
    public PicksPageRes findPicks(HttpServletRequest memberId, Pageable pageable)throws Exception{
        Optional<Member> findMember = memberRepository.findByEmail(jwtTokenProvider.getCurrentUser(memberId));
        return pickRepository.findPicksByMember(findMember.get(),pageable);
    }
    //픽 상세 조회
    public PickDetailRes findPick(long pickId){
        return pickRepository.findPickDetail(pickId);
    }
    //픽 삭제
    @Transactional
    public BaseResponseStatus changePickStatus(long id){
        Optional<Pick> findPick = pickRepository.findById(id);
        findPick.get().setStatus();

        return BaseResponseStatus.SUCCESS;
    }
    //픽 수정
    @Transactional
    public BaseResponseStatus changePickWriting(PickChangeReq pickChangeReq){
        Optional<Pick> findPick = pickRepository.findById(pickChangeReq.getId());
        findPick.get().setWriting(pickChangeReq.getWriting());
        return BaseResponseStatus.SUCCESS;
    }
}
