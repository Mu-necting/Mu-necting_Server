package com.munecting.server.domain.music.service;

import com.munecting.server.domain.music.dto.get.MusicSearchPageRes;
import com.munecting.server.domain.music.dto.get.MusicSearchRes;
import com.munecting.server.domain.music.repository.MusicRepository;
import com.munecting.server.global.utils.spotify.TokenSpotify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MusicService {
    private final MusicRepository musicRepository;
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(TokenSpotify.accessToken())
            .build();

    //music search
    public MusicSearchPageRes getMusicSearch(String search,int page){
        try {
            SearchItemRequest searchItemRequest = spotifyApi.searchItem(search, ModelObjectType.TRACK.getType())
                    .offset(page*20)
                    .build();
            SearchResult searchResult = searchItemRequest.execute();
            int totalPage = searchResult.getTracks().getTotal()/20+(searchResult.getTracks().getTotal()%20==0?0:1);
            return new MusicSearchPageRes(stream(searchResult.getTracks().getItems())
                    .map(track -> {
                        AlbumSimplified album = track.getAlbum();
                        return new MusicSearchRes(album.getName(), album.getArtists()[0].getName(), album.getImages()[0].getUrl());
                    })
                    .collect(Collectors.toList()),
                    totalPage-1);
        }catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
