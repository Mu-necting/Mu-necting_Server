package com.munecting.server.domain.archive.dto.get;

import lombok.Getter;
import se.michaelthelin.spotify.model_objects.specification.Image;

@Getter
public class MusicSearchRes {
    private Image[] name;
    public MusicSearchRes(Image[] name){
        this.name = name;
    }
}
