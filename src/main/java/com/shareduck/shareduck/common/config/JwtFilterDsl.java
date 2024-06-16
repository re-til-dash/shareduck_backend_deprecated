package com.shareduck.shareduck.common.config;

import com.shareduck.shareduck.common.redis.RedisRepository;
import com.shareduck.shareduck.common.security.filter.JwtAuthenticationFilter;
import com.shareduck.shareduck.common.security.filter.JwtVerificationFilter;
import com.shareduck.shareduck.common.security.handler.AuthenticationFailureCustomHandler;
import com.shareduck.shareduck.common.utils.jwt.JwtProperties;
import com.shareduck.shareduck.common.utils.jwt.JwtProvider;
import com.shareduck.shareduck.common.utils.response.CookieUtils;
import com.shareduck.shareduck.common.utils.translator.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtFilterDsl extends AbstractHttpConfigurer<JwtFilterDsl, HttpSecurity> {

    private final JwtProvider provider;
    private final JwtProperties properties;
    private final ObjectMapperUtils objectMapperUtils;
    private final RedisRepository repository;
    private final CookieUtils cookieUtils;
    private final AuthenticationFailureCustomHandler authenticationFailureCustomHandler;

    @Override
    public void configure(HttpSecurity builder) {
        AuthenticationManager authenticationManager =
                builder.getSharedObject(AuthenticationManager.class);

        JwtAuthenticationFilter jwtAuthenticationFilter =
                new JwtAuthenticationFilter(
                        provider, cookieUtils, objectMapperUtils, repository, properties);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureCustomHandler);

        JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(provider, properties);

        builder
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtVerificationFilter, JwtAuthenticationFilter.class);
    }

    public JwtFilterDsl build() {
        return this;
    }

}