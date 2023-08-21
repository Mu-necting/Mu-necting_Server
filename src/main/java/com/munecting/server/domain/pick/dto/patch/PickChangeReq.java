package com.munecting.server.domain.pick.dto.patch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickChangeReq {
    private String writing;
    private long id;
}
