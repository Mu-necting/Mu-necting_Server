package com.munecting.server.domain.music.controller;

import com.munecting.server.domain.music.dto.post.ReplyRequestDTO;
import com.munecting.server.domain.music.service.ReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/archives")
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/{archiveId}/reply")
    public ResponseEntity<String> createReply(@PathVariable Long archiveId, @RequestBody ReplyRequestDTO replyRequest) {
        replyService.reply(archiveId, replyRequest);

        Long memberId = replyRequest.getMemberId();
       // replyService.updateReplyTotalCnt(memberId);;
        replyService.updateReplyTotalCnt(archiveId);
        return ResponseEntity.ok("Replied");
    }
    @GetMapping("/{archiveId}/reply-count")
    public ResponseEntity<Integer> getReplyCount(@PathVariable Long archiveId) {
        int replyCount = replyService.getReplyCount(archiveId);
        return ResponseEntity.ok(replyCount);
    }


}
