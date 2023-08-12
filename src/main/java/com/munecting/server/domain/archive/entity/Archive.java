package com.munecting.server.domain.archive.entity;

import com.munecting.server.domain.BaseEntity;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.music.entity.Music;
import com.munecting.server.domain.pick.entity.Pick;
import com.munecting.server.domain.reply.entity.Reply;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "archive")
public class Archive extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private Member memberId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    @ToString.Exclude
    private Music musicId;
    private double pointX;   //(x,y) 좌표
    private double pointY;
    private int replyCnt;
    private LocalDateTime endTime;
    private String status = "ACTIVE";
    @OneToMany(mappedBy = "archiveId")
    @ToString.Exclude
    private List<Reply> replies = new ArrayList<>();
    @OneToMany(mappedBy = "archiveId")
    @ToString.Exclude
    private List<Pick> picks = new ArrayList<>();

    public void setPicks(List<Pick> picks) {
        this.picks = picks;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public void setMusicId(Music musicId) {
        this.musicId = musicId;
    }

    public void setMemberId(Member memberId) {
        this.memberId = memberId;
    }

    public Archive(Member memberId,double pointX,double pointY,LocalDateTime endTime){
        this.memberId = memberId;
        this.pointX = pointX;
        this.pointY = pointY;
        this.endTime = endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
