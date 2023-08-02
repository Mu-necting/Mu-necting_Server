package com.munecting.server.domain.music.service;

import com.munecting.server.domain.music.dto.get.MusicSSearchRes;
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
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MusicService {
    private final MusicRepository musicRepository;
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(TokenSpotify.accessToken())
            .build();
    //스포티파이 앨범 하나 data 가져오기
    public MusicSearchRes getMusicPlusSearch(String id){
        try {
            GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(id).build();
            Album album = getAlbumRequest.execute();
            log.info(album.getImages().toString());
            MusicSearchRes musicSearchRes = new MusicSearchRes(album.getImages());
            return musicSearchRes;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    //music search
    public List<MusicSSearchRes> getMusicSearch(String search){
        try {
            SearchItemRequest searchItemRequest = spotifyApi.searchItem(search, ModelObjectType.TRACK.getType())
                    .build();
            SearchResult searchResult = searchItemRequest.execute();

            List<MusicSSearchRes> musicSSearchResList = new ArrayList<>();
            for(Track track: searchResult.getTracks().getItems()){
                AlbumSimplified album = track.getAlbum();

                musicSSearchResList.add(new MusicSSearchRes(album.getName(),album.getArtists()[0].getName(),
                        album.getImages()[0].getUrl(),track.getPreviewUrl()));
            }
            return musicSSearchResList;
        }catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
