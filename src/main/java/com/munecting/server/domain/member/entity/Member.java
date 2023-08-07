package com.munecting.server.domain.member.entity;

import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.entity.Reply;
import com.munecting.server.domain.member.DTO.MemberDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;
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
public class Member implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// , generator = "user_sequence"
    private Long userIdx;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "intro", nullable = false)
    private String intro;

    @Column(name = "profileImage", nullable = true)
    private String profileImage;

    @Column(name = "status", nullable = false)
    @ColumnDefault("'A'") // A: 활성 유저 D: 탈퇴 유저
    private char status;

    @Column(name = "role", nullable = false) // User
    private String role;

    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime created_at;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updated_at;

    @Column(name = "login_at", nullable = true)
    private LocalDateTime login_at;

    @Column(name = "login_cnt", nullable = false)
    @ColumnDefault("0")
    private Long login_cnt;

    @PrePersist
    public void create_at(){
        this.created_at = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "memberId")
    private List<Genre> genres = new ArrayList<>();
    @OneToMany(mappedBy = "memberId")
    private List<Archive> archives = new ArrayList<>();
    @OneToMany(mappedBy = "memberId")
    private List<Reply> members = new ArrayList<>();

    public MemberDTO toDTO() {
        return MemberDTO.builder()
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
    }}
