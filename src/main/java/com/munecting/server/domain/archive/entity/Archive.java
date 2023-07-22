package com.munecting.server.domain.archive.entity;

import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.music.entity.Music;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "archive")
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music musicId;
    private Float pointX;   //(x,y) 좌표
    private Float pointY;
    private int replyCnt;
    private String writing;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private LocalDateTime updateAt;
    @OneToMany(mappedBy = "archiveId")
    private List<HashTag> genres = new ArrayList<>();
    @OneToMany(mappedBy = "archiveId")
    private List<Reply> replies = new ArrayList<>();
}
