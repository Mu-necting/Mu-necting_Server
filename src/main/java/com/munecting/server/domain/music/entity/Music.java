package com.munecting.server.domain.music.entity;

import com.munecting.server.domain.BaseEntity;
import com.munecting.server.domain.archive.entity.Archive;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "music")
public class Music extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_id")
    private Long id;
    private String name;
    private String coverImg;
    private String musicPre; //spotify 30s
    private String musicPull; // YouTube link full
    @Enumerated(EnumType.STRING)
    private MusicGenre genre;
    private String artist;
    private String status;
    @OneToOne(mappedBy = "musicId")
    private Archive archives;
}