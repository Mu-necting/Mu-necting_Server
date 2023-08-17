package com.munecting.server.domain.member.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TokenDTO {

    private String types;
    private String token;
    private Date tokenExpiresTime;
}