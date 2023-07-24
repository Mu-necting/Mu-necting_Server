package com.munecting.server.domain.archive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArchivePlus {
    private Long musicId;
    private Long memberId;
    private String writing;
    private String createAt;
}
