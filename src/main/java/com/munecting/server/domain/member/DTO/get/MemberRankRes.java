package com.munecting.server.domain.member.DTO.get;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberRankRes {
    private String profile;
    private String nick;
    private long allReplyCnt;
    private long rank;
}
