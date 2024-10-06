package com.shareduck.shareduck.common.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shareduck.shareduck.common.redis.RedisRepository;
import com.shareduck.shareduck.common.security.service.AuthService;
import com.shareduck.shareduck.common.utils.jwt.JwtProperties;
import com.shareduck.shareduck.common.utils.jwt.JwtProvider;
import com.shareduck.shareduck.common.utils.response.CookieProperties;
import com.shareduck.shareduck.common.utils.response.CookieUtils;
import com.shareduck.shareduck.common.utils.translator.ObjectMapperUtils;
import com.shareduck.shareduck.domain.user.persistence.repository.UserRepository;
import org.mockito.Mockito;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@EnableConfigurationProperties({JwtProperties.class, CookieProperties.class})
public class AuthServiceTestConfig {

    private final JwtProperties jwtProperties;
    private final CookieProperties cookieProperties;

    public AuthServiceTestConfig(JwtProperties jwtProperties, CookieProperties cookieProperties) {
        this.jwtProperties = jwtProperties;
        this.cookieProperties = cookieProperties;
    }

    @Bean
    public RedisRepository redisRepository() {
        return Mockito.mock(RedisRepository.class);
    }

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(jwtProperties);
    }

    @Bean
    public ObjectMapperUtils objectMapperUtils() {
        return Mockito.mock(ObjectMapperUtils.class);
    }

    @Bean
    public CookieUtils cookieUtils() {
        return new CookieUtils(cookieProperties);
    }

    @Bean
    public AuthService authService() {
        return new AuthService(redisRepository(), jwtProvider(), jwtProperties, userRepository(),
            objectMapperUtils(), cookieUtils());
    }
}
