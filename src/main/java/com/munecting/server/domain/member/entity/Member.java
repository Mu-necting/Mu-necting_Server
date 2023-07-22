package com.munecting.server.domain.member.entity;

import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.entity.Reply;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(nullable = false)
    private String pw;
    @Column
    private String nickname;
    @Column
    private String email;
    @Column(columnDefinition = "char(11)")
    private Character phone;
    private String profile;
    @Column(length = 1)
    private int pushAlarm;
    private String intro;
    private String status;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private LocalDateTime updateAt;
    @OneToMany(mappedBy = "memberId")
    private List<Genre> genres = new ArrayList<>();
    @OneToMany(mappedBy = "memberId")
    private List<Archive> archives = new ArrayList<>();
    @OneToMany(mappedBy = "memberId")
    private List<Reply> members = new ArrayList<>();
}
