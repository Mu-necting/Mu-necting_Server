package com.munecting.server.domain.member.DTO;

import com.munecting.server.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private Long userIdx;

    private String email;

    private String password;

    private String name;

    private Long replyTotalCnt;

    private String profileImage;

    private char status;

    private String role;

    private LocalDateTime login_at;

    private Long login_cnt;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .replyTotalCnt(replyTotalCnt)
                .profileImage(profileImage)
                .status(status)
                .role(role)
                .login_at(login_at)
                .login_cnt(login_cnt)
                .build();
    }
}
