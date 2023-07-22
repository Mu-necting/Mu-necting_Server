package com.munecting.server.domain.music.entity;

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
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_id")
    private Long id;
    private String name;
    private String coverImg;
    private String musicLink;
    @Enumerated(EnumType.STRING)
    private MusicGenre genre;
    private String artist;
    private String status;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private LocalDateTime updateAt;
    @OneToMany(mappedBy = "musicId")
    private List<Archive> archives = new ArrayList<>();
}