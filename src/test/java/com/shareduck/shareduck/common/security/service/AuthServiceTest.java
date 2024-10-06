package com.shareduck.shareduck.common.security.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.shareduck.shareduck.common.exception.BusinessLogicException;
import com.shareduck.shareduck.common.redis.RedisRepository;
import com.shareduck.shareduck.common.security.config.AuthServiceTestConfig;
import com.shareduck.shareduck.common.security.dto.UserInfo;
import com.shareduck.shareduck.common.security.exception.AuthExceptionCode;
import com.shareduck.shareduck.common.utils.translator.ObjectMapperUtils;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(AuthServiceTestConfig.class)
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private RedisRepository redis;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapperUtils objectMapperUtils;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Cookie cookie;
    private String content;
    private UserInfo info;
    private UserEntity user;

    @BeforeEach
    void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        cookie = new Cookie("Refresh", "refreshToken ");
        content = "redisSavedContent";
        info = new UserInfo("test@gmail.com", "Role_User");
        user = UserEntity.builder()
            .id(1L)
            .email("test@gmail.com")
            .build();
    }

    @Test
    void reissue_Success_Test() {
        //given
        given(request.getCookies()).willReturn(new Cookie[]{cookie});
        given(objectMapperUtils.toEntity(anyString(), any())).willReturn(info);
        given(redis.findByKey(anyString())).willReturn(content);
        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(user));

        //when
        authService.reissue(request, response);

        //then
        verify(response).setHeader(Mockito.eq(HttpHeaders.AUTHORIZATION), anyString());
        verify(response).setHeader(Mockito.eq("Refresh"), anyString());
        verify(response).addCookie(any(Cookie.class));
    }

    @Test
    void reissue_Refresh_Header_Success_Test() {
        //given
        given(request.getCookies()).willReturn(null);
        given(request.getHeader("Refresh")).willReturn("refreshToken");
        given(objectMapperUtils.toEntity(anyString(), any())).willReturn(info);
        given(redis.findByKey(anyString())).willReturn(content);
        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(user));

        //when
        authService.reissue(request, response);

        //then
        verify(response).setHeader(Mockito.eq(HttpHeaders.AUTHORIZATION), anyString());
        verify(response).setHeader(Mockito.eq("Refresh"), anyString());
        verify(response).addCookie(any(Cookie.class));
    }

    @Test
    void reissue_Expire_Redis_test() {
        //given
        given(request.getCookies()).willReturn(new Cookie[]{cookie});
        given(objectMapperUtils.toEntity(anyString(), any())).willReturn(info);
        given(redis.findByKey(anyString())).willReturn(null);
        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(user));

        //when then
        assertThatThrownBy(() -> authService.reissue(request, response))
            .isInstanceOf(BusinessLogicException.class)
            .hasMessageContaining(AuthExceptionCode.REFRESH_TOKEN_NOT_FOUND.getMessage());
    }
}
