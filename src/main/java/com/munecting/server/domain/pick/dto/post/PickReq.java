package com.munecting.server.domain.pick.dto.post;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickReq {
    private String writing;
    private long archiveId;
}
