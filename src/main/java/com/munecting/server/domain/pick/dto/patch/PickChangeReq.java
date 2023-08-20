package com.munecting.server.domain.pick.dto.patch;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PickChangeReq {
    private String writing;
    private long id;
}
