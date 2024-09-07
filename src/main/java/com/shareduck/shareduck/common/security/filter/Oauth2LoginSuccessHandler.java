package com.shareduck.shareduck.common.security.filter;

import com.shareduck.shareduck.common.redis.RedisRepository;
import com.shareduck.shareduck.common.security.dto.UserInfo;
import com.shareduck.shareduck.common.security.vo.Principal;
import com.shareduck.shareduck.common.utils.jwt.JwtProperties;
import com.shareduck.shareduck.common.utils.jwt.JwtProvider;
import com.shareduck.shareduck.common.utils.response.CookieUtils;
import com.shareduck.shareduck.common.utils.translator.ObjectMapperUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class Oauth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final CookieUtils cookieUtils;
    private final ObjectMapperUtils objectMapper;
    private final RedisRepository repository;
    private final JwtProperties jwtProperties;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication){

        Principal principal = (Principal) authentication.getPrincipal();

        String accessToken = createAccessToken(principal);
        String refreshToken = createRefreshToken(principal);

        repository.save(
            refreshToken,
            objectMapper.toStringValue(createUserInfo(principal)),
            jwtProperties.getRefreshTokenValidityInSeconds());

        response.setHeader(HttpHeaders.AUTHORIZATION, jwtProperties.getPrefix() + accessToken);
        response.setHeader("Refresh", refreshToken);
        response.addCookie(cookieUtils.createCookie(refreshToken));
    }

    private String createRefreshToken(Principal principal) {
        return jwtProvider.generateRefreshToken(principal.getUsername());
    }

    private String createAccessToken(Principal principal) {
        return jwtProvider.generateAccessToken(
            principal.getUsername(), principal.getId(), toTrans(principal.getAuthorities()));
    }

    private UserInfo createUserInfo(Principal principal) {
        return new UserInfo(principal.getUsername(), toTrans(principal.getAuthorities()));
    }

    private String toTrans(Collection<? extends GrantedAuthority> list) {
        return StringUtils.collectionToCommaDelimitedString(list);
    }
}
