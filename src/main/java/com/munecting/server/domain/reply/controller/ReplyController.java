package com.munecting.server.domain.reply.controller;

import com.munecting.server.domain.reply.dto.post.ReplyRequestDTO;
import com.munecting.server.domain.reply.service.ReplyService;
import com.munecting.server.global.config.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/reply")
    public ResponseEntity<BaseResponse<String>> createReply(@RequestParam Long archiveId, @RequestParam Long memberId) {
        ReplyRequestDTO replyRequest = new ReplyRequestDTO();
        replyRequest.setMemberId(memberId);
        replyService.reply(archiveId, replyRequest);
        replyService.updateReplyTotalCnt();
        return ResponseEntity.ok(new BaseResponse<>(true, "요청에 성공하였습니다.", 1000, "Replied"));
    }

    @GetMapping("/reply-count")
    public ResponseEntity<BaseResponse<Map<String, Object>>> getReplyCount(@RequestParam Long archiveId) {
        int replyCount = replyService.getReplyCount(archiveId);

        Map<String, Object> result = new HashMap<>();
        result.put("replyCnt", replyCount);

        return ResponseEntity.ok(new BaseResponse<>(true, "요청에 성공하였습니다.", 1000, result));
    }

    @PostMapping("/unreply")
    public ResponseEntity<BaseResponse<String>> unreply(@RequestParam Long archiveId, @RequestParam Long memberId) {
        replyService.unreply(archiveId, memberId);
        return ResponseEntity.ok(new BaseResponse<>(true, "요청에 성공하였습니다.", 1000, "Unreplied"));
    }



    @GetMapping("/reply-senders")
    public ResponseEntity<BaseResponse<Map<String, Object>>> getReplySendersForMember(@RequestParam Long memberId) {
        Set<String> senderNames = replyService.getReplySendersForMember(memberId);

        Map<String, Object> result = new HashMap<>();
        result.put("senderNames", senderNames);

        BaseResponse<Map<String, Object>> response = new BaseResponse<>(true, "요청에 성공하였습니다.", 1000, result);

        return ResponseEntity.ok(response);
    }

}