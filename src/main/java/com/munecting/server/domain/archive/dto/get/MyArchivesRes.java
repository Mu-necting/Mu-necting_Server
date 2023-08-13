package com.munecting.server.domain.archive.dto.get;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyArchivesRes {
    private String coverImg;
    private long archiveId;
}
