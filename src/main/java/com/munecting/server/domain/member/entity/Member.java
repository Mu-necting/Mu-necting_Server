package com.munecting.server.domain.member.entity;

import com.munecting.server.domain.BaseEntity;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.pick.entity.Pick;
import com.munecting.server.domain.reply.entity.Reply;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity {
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
    @OneToMany(mappedBy = "memberId")
    private List<Archive> archives = new ArrayList<>();
    @OneToMany(mappedBy = "memberId")
    private List<Reply> replies = new ArrayList<>();
    @OneToMany(mappedBy = "memberId")
    private List<Pick> picks = new ArrayList<>();
}
