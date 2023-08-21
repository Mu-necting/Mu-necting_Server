package com.munecting.server.domain.member.DTO;

import lombok.Data;

@Data
public class VerifyDTO {
    private String email;
    private String code;
}

