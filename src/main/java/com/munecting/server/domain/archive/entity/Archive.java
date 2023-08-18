package com.munecting.server.domain.archive.entity;

import com.munecting.server.domain.BaseEntity;
import com.munecting.server.domain.member.entity.Member;
import com.munecting.server.domain.music.entity.Music;
import com.munecting.server.domain.pick.entity.Pick;
import com.munecting.server.domain.reply.entity.Reply;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private Member memberId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music musicId;
    @Column(name = "point_x")
    private float pointX;   //(x,y) 좌표
    @Column(name = "point_y")
    private float pointY;
    private int replyCnt;
    public int getReplyCnt() {
        return replyCnt;
    }

    public void increaseReplyCnt() {
        this.replyCnt++;
    }
    public void decreaseReplyCnt() {
        this.replyCnt--;
    }
    private LocalDateTime endTime;
    private String status = "ACTIVE";
    @OneToMany(mappedBy = "archiveId")
    private List<Reply> replies = new ArrayList<>();
    @OneToMany(mappedBy = "archiveId")
    private List<Pick> picks = new ArrayList<>();
    public Archive(Member memberId,Music musicId,float pointX,float pointY,LocalDateTime endTime){
        this.memberId = memberId;
        this.musicId = musicId;
        this.pointX = pointX;
        this.pointY = pointY;
        this.endTime = endTime;
    }

}
