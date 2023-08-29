package com.munecting.server.domain.reply.dto.post;
import com.munecting.server.domain.member.entity.Member;

public class ReplyRequestDTO {
    private Long replyId;
    private Long memberId;
    // Getter, Setter
    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }





}
