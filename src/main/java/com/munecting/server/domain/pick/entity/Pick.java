package com.munecting.server.domain.pick.entity;

import com.munecting.server.domain.BaseEntity;
import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "pick")
public class Pick extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pick_id")
    private Long id;
    private String writing;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "archive_id")
    private Archive archiveId;
    private String status;
}
