package com.munecting.server.domain.music.controller;

import com.munecting.server.domain.music.dto.post.ReplyRequestDTO;
import com.munecting.server.domain.music.service.ReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/reply")
    public ResponseEntity<String> createReply(@RequestParam Long archiveId, @RequestParam Long memberId) {
        ReplyRequestDTO replyRequest = new ReplyRequestDTO();
        replyRequest.setMemberId(memberId);
        replyService.reply(archiveId, replyRequest);
        replyService.updateReplyTotalCnt(archiveId);
        return ResponseEntity.ok("Replied");
    }


    @GetMapping("/reply-count")
    public ResponseEntity<Integer> getReplyCount(@RequestParam Long archiveId) {
        int replyCount = replyService.getReplyCount(archiveId);
        return ResponseEntity.ok(replyCount);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<String>> getRankedMembersByReplyTotalCnt() {
        List<String> rankedMembersWithNames = replyService.getRankedMembersWithNames();
        return ResponseEntity.ok(rankedMembersWithNames);
    }

    @PostMapping("/unreply")
    public ResponseEntity<String> unreply(@RequestParam Long archiveId, @RequestParam Long memberId) {
        replyService.unreply(archiveId, memberId);
        return ResponseEntity.ok("Reply canceled");
    }
}
