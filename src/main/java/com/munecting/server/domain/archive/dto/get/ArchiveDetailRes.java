package com.munecting.server.domain.archive.dto.get;

import com.munecting.server.domain.music.entity.MusicGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveDetailRes {
    private String coverImg;
    private MusicGenre genre;
    private String name;
    private String artist;
    private long replyCnt;
    private long pickCnt;
    private LocalDateTime createAt;
}
