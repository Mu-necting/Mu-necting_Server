package com.munecting.server.domain.member.entity;

import com.munecting.server.domain.BaseEntity;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.member.DTO.MemberDTO;
import com.munecting.server.domain.reply.entity.Reply;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "Member")
@Table(name = "Member")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// , generator = "user_sequence"
    private Long userIdx;

    @Column(name = "email", nullable = true, unique = false)
    private String email;

    @Column(name = "password", nullable = true) //nullable
    private String password;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "all_replyCnt", nullable = false)
    private Long all_replyCnt;

    @Column(name = "profileImage", nullable = true)
    private String profileImage;

    @Column(name = "status", nullable = false)
    @ColumnDefault("'A'") // A: 활성 유저 D: 탈퇴 유저
    private char status;

    @Column(name = "role", nullable = true) // User
    private String role;

    @Column(name = "login_at", nullable = true)
    private LocalDateTime login_at;

    @Column(name = "login_cnt", nullable = false)
    private Long login_cnt;

    @OneToMany(mappedBy = "memberId")
    private List<Archive> archives = new ArrayList<>();
    @OneToMany(mappedBy = "memberId")
    private List<Reply> members = new ArrayList<>();

    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .email(email)
                .password(password)
                .name(name)
                .all_replyCnt(all_replyCnt)
                .profileImage(profileImage)
                .status(status)
                .role(role)
                .login_at(login_at)
                .login_cnt(login_cnt)
                .build();
    }

    public Member(String name){
        this.name=name;
    }
    public Member(String name, Long all_replyCnt){
        this.name=name;
        this.all_replyCnt=all_replyCnt;
    }

    // UserDetails 상속
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
