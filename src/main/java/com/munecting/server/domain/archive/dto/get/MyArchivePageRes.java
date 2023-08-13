package com.munecting.server.domain.archive.dto.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyArchivePageRes {
    private List<MyArchivesRes> archivesRes;
    private long totalCnt;
}
