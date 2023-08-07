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

    private String phone;

    private String intro;

    private String profileImage;

    private char status;

    private String role;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private LocalDateTime login_at;

    private Long login_cnt;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .intro(intro)
                .profileImage(profileImage)
                .status(status)
                .role(role)
                .created_at(created_at)
                .updated_at(updated_at)
                .login_at(login_at)
                .login_cnt(login_cnt)
                .build();
    }
}
