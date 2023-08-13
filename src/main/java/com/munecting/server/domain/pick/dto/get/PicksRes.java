package com.munecting.server.domain.pick.dto.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PicksRes {
    private String coverImg;
    private long pickId;
}
