package com.munecting.server.domain.archive.dto.get;

import com.munecting.server.domain.music.entity.MusicGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapArchiveRes {
    private double pointX;
    private double pointY;
    private MusicGenre genre;
    private String artist;
    private String name;
}
