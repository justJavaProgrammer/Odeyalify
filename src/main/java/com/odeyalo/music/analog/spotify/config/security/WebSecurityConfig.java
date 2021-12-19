package com.odeyalo.music.analog.spotify.config.security;

import com.odeyalo.music.analog.spotify.constants.UrlConstants;
import com.odeyalo.music.analog.spotify.config.security.filters.TokenAuthenticationFilter;
import com.odeyalo.music.analog.spotify.config.security.jwt.JwtAuthenticationEntryPoint;
import com.odeyalo.music.analog.spotify.services.register.support.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    private final TokenAuthenticationFilter jwtFilter;
    public static final String REGISTER_ANT_MATCHER = "/auth/signup/**";
    public static final String LOGIN_ANT_MATCHER = "/auth/login/**";
    public static final String REFRESH_TOKEN_ANT_MATCHER = "/auth/refreshtoken";
    public static final String ROOT_ANT_MATCHER = "/";
    public static final String UPLOAD_GET_ANT_MATCHER = "/upload";
    public static final String UPLOAD_POST_ANT_MATCHER = "/upload/**";
    public static final String INFO_ANT_MATCHER = "/info/**";
    public static final String STREAM_DATA_MATCHER = "/stream/audio/**";
    public static final String PATH_TO_RESOURCES_MATCHER = "/resources/**";
    public static final String SEARCH_DATA_MATCHER = "/search/song/**";

    @Autowired
    public WebSecurityConfig(TokenAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(PATH_TO_RESOURCES_MATCHER,
                        ROOT_ANT_MATCHER, REGISTER_ANT_MATCHER,
                        LOGIN_ANT_MATCHER, REFRESH_TOKEN_ANT_MATCHER, STREAM_DATA_MATCHER).permitAll()
                .antMatchers(HttpMethod.GET, UPLOAD_GET_ANT_MATCHER).permitAll()
                .antMatchers(HttpMethod.POST, UrlConstants.DEFAULT_UPLOAD_SONG_URL).hasAuthority("ARTIST")
                .antMatchers(HttpMethod.POST, UrlConstants.DEFAULT_UPLOAD_ALBUM_URL).hasAuthority("ARTIST")
                .anyRequest().authenticated()
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .anonymous().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint());

        logger.info("Security configuration successful");
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(getUserDetailsService());
        provider.setPasswordEncoder(getPasswordEncoder());
        return provider;
    }
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
//        return memory;
//    }

}
