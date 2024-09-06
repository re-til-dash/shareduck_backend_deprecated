package com.shareduck.shareduck.common.security.vo.oauth.impl;

import com.shareduck.shareduck.common.security.vo.oauth.OAuth2UserInfo;
import com.shareduck.shareduck.domain.user.persistence.enums.ProviderEnum;
import java.util.Map;
import lombok.Getter;

@Getter
public class GoogleUserInfo extends OAuth2UserInfo {
    private final ProviderEnum provider;

    public GoogleUserInfo(Map<String, Object> attributes, String userNameAttributeName) {
        super(attributes, userNameAttributeName);
        this.provider = ProviderEnum.GOOGLE;
    }


    @Override
    public String getSocialId() {
        return attributes.get("registrationId").toString();
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getProfile() {
        return attributes.get("picture").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }
}
