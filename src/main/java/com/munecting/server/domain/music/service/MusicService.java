package com.munecting.server.domain.music.service;
import com.munecting.server.domain.music.dto.get.YoutubeApiRes;

import com.munecting.server.domain.archive.entity.Archive;
import com.munecting.server.domain.archive.repository.ArchiveRepository;
import com.munecting.server.domain.member.repository.MemberRepository;
import com.munecting.server.domain.music.dto.get.MusicSearchPageRes;
import com.munecting.server.domain.music.dto.get.MusicSearchRes;
import com.munecting.server.domain.music.dto.post.UploadMusicReq;
import com.munecting.server.domain.music.entity.Music;
import com.munecting.server.domain.music.repository.MusicRepository;
import com.munecting.server.global.utils.spotify.TokenSpotify;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;
import static java.util.Arrays.stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MusicService {
    private final MusicRepository musicRepository;
    private final ArchiveRepository archiveRepository;
    private final MemberRepository memberRepository;

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(TokenSpotify.accessToken())
            .build();

    //음악 검색
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
    //아카이브 업로드
    @Transactional
    public void postMusicArchive(UploadMusicReq uploadMusicReq){
        musicRepository.postMusic(new Music(uploadMusicReq.getName(),uploadMusicReq.getCoverImg(),
                uploadMusicReq.getMusicPre(),uploadMusicReq.getMusicPull(),uploadMusicReq.getGenre(),uploadMusicReq.getArtist()));
    }


    @Value("${youtube.apiKey}")
    private String youtubeApiKey;

    public String getYoutubeMusicLink(String music) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://www.googleapis.com/youtube/v3/search";

        try {
            ResponseEntity<YoutubeApiRes> responseEntity = restTemplate.getForEntity(
                    apiUrl + "?key=" + youtubeApiKey + "&q=" + music + "&type=video",
                    YoutubeApiRes.class);

            YoutubeApiRes apiResponse = responseEntity.getBody();

            log.info("API Response: {}", apiResponse);

            if (apiResponse != null && apiResponse.getItems() != null && !apiResponse.getItems().isEmpty()) {
                String videoId = apiResponse.getItems().get(0).getVideoId();
                log.info("Video ID: {}", videoId); // 추가: Video ID 확인
                return "https://www.youtube.com/watch?v=" + videoId;
            } else {
                log.warn("YouTube API response is empty or null.");
                return null;
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching YouTube link.", e);
            return null;
        }
    }


    /*
    @Value("${youtube.apiKey}")
    private String youtubeApiKey;
    public String getYoutubeMusicLink(String music) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://www.googleapis.com/youtube/v3/search";

        // YouTube API 호출
        ResponseEntity<YoutubeApiRes> responseEntity = restTemplate.getForEntity(
                apiUrl + "?key=" + youtubeApiKey + "&q=" + music + "&type=video",
                YoutubeApiRes.class);

        YoutubeApiRes apiResponse = responseEntity.getBody();

        if (apiResponse != null && apiResponse.getItems() != null && !apiResponse.getItems().isEmpty()) {

            String videoId = apiResponse.getItems().get(0).getVideoId();
            return "https://www.youtube.com/watch?v=" + videoId;
        } else {
            System.out.println("YouTube API response is empty or null.");
            return null;
        }
    }



    @Transactional
    public void getAndSaveYoutubeLink(String musicName) {
        String youtubeLink = getYoutubeMusicLink(musicName);
        Long musicId = musicRepository.findMusicIdByName(musicName);
        updateMusicFullLink(musicId, youtubeLink);
    }
    @Transactional
    public void updateMusicFullLink(Long musicId, String youtubeLink) {
        Music music = musicRepository.findById(musicId);
        music.setMusicFull(youtubeLink);
        musicRepository.postMusic(music);
    }



*/

}
