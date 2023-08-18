package com.munecting.server.domain.member.DTO;

import lombok.Data;

@Data
public class ChangePwInfo {
    private String newPw;
    private String newPwCheck;
}
