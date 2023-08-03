package com.munecting.server.domain.archive.entity;

import com.munecting.server.domain.BaseEntity;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.music.entity.Music;
import com.munecting.server.domain.pick.entity.Pick;
import com.munecting.server.domain.reply.entity.Reply;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "archive")
public class Archive extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music musicId;
    private Float pointX;   //(x,y) 좌표
    private Float pointY;
    private int replyCnt;
    private LocalDateTime endTime;
    private String status;
    @OneToMany(mappedBy = "archiveId")
    private List<Reply> replies = new ArrayList<>();
    @OneToMany(mappedBy = "archiveId")
    private List<Pick> picks = new ArrayList<>();
}
