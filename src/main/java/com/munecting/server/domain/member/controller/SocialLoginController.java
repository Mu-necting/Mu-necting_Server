package com.munecting.server.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.munecting.server.domain.member.entity.OAuthToken;
import com.munecting.server.domain.member.service.SocialLoginService;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RestController
public class SocialLoginController {
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;
    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private String authType;

    @Autowired
    private SocialLoginService socialLoginService;
    // ----- 소셜 로그인 관련
    @GetMapping("/oauth2/kakao")
    public @ResponseBody String kakaoCallback(String accesstoken) throws ParseException {

//        RestTemplate rt = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", authType);
//        params.add("client_id", clientId);
//        params.add("client_secret", clientSecret);
//        params.add("redirect_uri", redirectUri);
//        params.add("code", code);
//
//        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
//
//        ResponseEntity<String> response = rt.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                kakaoTokenRequest,
//                String.class
//        );
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        OAuthToken oAuthToken = null;
//
//        try {
//            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        System.out.println("카카오 액세스 토큰 : " + oAuthToken.getAccess_token());



        RestTemplate rt2 = new RestTemplate();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer "+ accesstoken);
        headers2.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(null, headers2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );


        JSONObject profileJson = new JSONObject(response2.getBody());
        JSONObject kakaoAccount = profileJson.getJSONObject("kakao_account");
        String name = kakaoAccount.getJSONObject("profile").getString("nickname");
        String email = kakaoAccount.getString("email");
        String image = kakaoAccount.getJSONObject("profile").getString("profile_image_url");
        log.info(socialLoginService.socialLogin(email, name, image));



        return response2.getBody();

    }
}