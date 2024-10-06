package com.shareduck.shareduck.common.security.service;

import com.shareduck.shareduck.common.security.vo.oauth.OAuth2UserInfo;
import com.shareduck.shareduck.common.security.vo.Principal;
import com.shareduck.shareduck.common.security.vo.oauth.impl.GoogleUserInfo;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl extends DefaultOAuth2UserService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity findUser =
                repository
                        .findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return Principal.jwt(findUser);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
            .getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2UserInfo oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes(),
            userNameAttributeName);

        UserEntity user = repository.findByEmail(oauth2UserInfo.getEmail())
            .orElse(new UserEntity());

        user.oauth(oauth2UserInfo.toEntity());

        return Principal.oauth(user,oauth2UserInfo.getAttribute(),oauth2UserInfo.getNameAttributeKey());
    }
}