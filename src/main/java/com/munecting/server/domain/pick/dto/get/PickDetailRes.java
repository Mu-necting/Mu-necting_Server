package com.munecting.server.domain.pick.dto.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString(of = {"name","artist","id"})
@AllArgsConstructor
public class PickDetailRes {
    private String name;
    private String artist;
    private LocalDateTime createAt;
    private String coverImg;
    private String writing;
}
