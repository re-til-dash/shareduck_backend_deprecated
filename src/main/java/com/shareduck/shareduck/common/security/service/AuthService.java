package com.shareduck.shareduck.common.security.service;

import com.shareduck.shareduck.common.exception.BusinessLogicException;
import com.shareduck.shareduck.common.redis.RedisRepository;
import com.shareduck.shareduck.common.security.dto.UserInfo;
import com.shareduck.shareduck.common.security.exception.AuthExceptionCode;
import com.shareduck.shareduck.common.utils.jwt.JwtProperties;
import com.shareduck.shareduck.common.utils.jwt.JwtProvider;
import com.shareduck.shareduck.common.utils.response.CookieUtils;
import com.shareduck.shareduck.common.utils.translator.ObjectMapperUtils;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RedisRepository redis;
    private final JwtProvider provider;
    private final JwtProperties properties;
    private final UserRepository userRepository;
    private final ObjectMapperUtils objectMapperUtils;
    private final CookieUtils cookieUtils;

    public void reissue(HttpServletRequest request, HttpServletResponse response) {
        UserEntity findUser = validRefreshTokenSubject(findUserInfoData(request));

        String refresh = getRefreshToken(findUser);
        String access = getAccessToken(findUser, findUserInfoData(request));

        saveUserInfoToRedis(refresh, findUserInfoData(request));

        response.setHeader(HttpHeaders.AUTHORIZATION, access);
        response.setHeader(properties.getRefreshHeader(), refresh);
        response.addCookie(cookieUtils.createCookie(refresh));
    }


    //get Refresh Token
    private UserInfo findUserInfoData(HttpServletRequest request) {
        String refresh = getRefreshFromRequest(request);

        return findUserInfo(refresh);
    }

    private String getRefreshFromRequest(HttpServletRequest request){
        if(request.getHeader(properties.getRefreshHeader()) != null){
            return request.getHeader(properties.getRefreshHeader());
        }else
            return findTokenToRedis(cookieUtils.searchCookieProperties(request));
    }

    private String getRefreshToken(UserEntity findUser) {
        return provider.generateRefreshToken(findUser.getEmail());
    }

    private String getAccessToken(UserEntity findUser, UserInfo info) {
        return properties.getPrefix()
                + provider.generateAccessToken(
                findUser.getEmail(), findUser.getId(), info.getAuthorities());
    }

    private void saveUserInfoToRedis(String refresh, UserInfo info) {
        redis.save(
                refresh,
                objectMapperUtils.toStringValue(info),
                provider.getRefreshTokenValidityInSeconds());
    }

    private UserEntity validRefreshTokenSubject(UserInfo userInfo) {
        return userRepository
                .findByEmail(userInfo.getUserName())
                .orElseThrow(() -> new BusinessLogicException(AuthExceptionCode.USER_NOT_FOUND));
    }

    private UserInfo findUserInfo(String refresh) {
        return objectMapperUtils.toEntity(findAndDeleteToRedis(refresh), UserInfo.class);
    }

    private String findAndDeleteToRedis(String refresh) {
        deleteToken(refresh);
        return refresh;
    }

    private void deleteToken(String tokenToRedis) {
        redis.delete(tokenToRedis);
    }

    private String findTokenToRedis(Cookie refreshCookie) {
        return Optional.ofNullable(redis.findByKey(refreshCookie.getValue()))
                .orElseThrow(() -> new BusinessLogicException(AuthExceptionCode.REFRESH_TOKEN_NOT_FOUND));
    }
}