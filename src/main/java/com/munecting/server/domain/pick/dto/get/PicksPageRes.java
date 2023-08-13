package com.munecting.server.domain.pick.dto.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PicksPageRes {
    private List<PicksRes> picksRes;
    private long totalPage;

}
