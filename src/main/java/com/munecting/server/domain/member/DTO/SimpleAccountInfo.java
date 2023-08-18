package com.munecting.server.domain.member.DTO;

import lombok.Data;

@Data
public class SimpleAccountInfo {
    private Long userIdx;
    private String email;
    private String name;
    private String profileImage;


    public SimpleAccountInfo(Long userIdx, String email, String name, String profileImage) {
        this.userIdx = userIdx;
        this.email = email;
        this.name = name;
        this.profileImage = profileImage;
    }
}