package com.munecting.server.global.config.secure.WebConfig;


import com.munecting.server.global.config.secure.JWT.JwtAuthenticationFilter;
import com.munecting.server.global.config.secure.JWT.JwtTokenProvider;
import com.munecting.server.global.config.secure.SocialConfig.OAuth2AuthenticationSuccessHandler;
import com.munecting.server.global.config.secure.SocialConfig.UserOAuth2Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final UserOAuth2Service userOAuth2Service;

    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider, RedisTemplate redisTemplate,
                             OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler,
                             UserOAuth2Service userOAuth2Service) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTemplate = redisTemplate;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
        this.userOAuth2Service = userOAuth2Service;
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .httpBasic().disable() // loginform 방식으로 구현해야함.
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(authorizeRequests ->
                                authorizeRequests
//                                .requestMatchers(HttpMethod.POST, "/join").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                        .requestMatchers("/**").permitAll()
                                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .defaultSuccessUrl("/login-success")
                                .successHandler(oAuth2AuthenticationSuccessHandler)
                                .userInfoEndpoint(userInfo ->
                                        userInfo
                                                .userService(userOAuth2Service)
                                )
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisTemplate), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
