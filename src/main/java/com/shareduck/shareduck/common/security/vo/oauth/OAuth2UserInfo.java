package com.shareduck.shareduck.common.security.vo.oauth;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.enums.ProviderEnum;
import com.shareduck.shareduck.domain.user.persistence.enums.UserRole;
import java.util.Map;
import java.util.UUID;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;
    private String userNameAttributeName;

    protected OAuth2UserInfo(Map<String, Object> attributes,String userNameAttributeName) {
        this.attributes = attributes;
        this.userNameAttributeName = userNameAttributeName;
    }

    public abstract String getSocialId();

    public abstract String getEmail();

    public abstract String getProfile();

    public abstract String getName();

    //TODO : PASSWORD 변경 필수
    public UserEntity toEntity() {
        return UserEntity.builder()
            .email(getEmail())
            .role(UserRole.USER)
            .name(getName())
            .idx(UUID.randomUUID().toString())
            .provider(getProvider())
            .profile(getProfile())
            .password(UUID.randomUUID().toString())
            .build();
    }
    public String getNameAttributeKey() {
        return userNameAttributeName;
    }
    public Map<String, Object> getAttribute(){
        return this.attributes;
    }

    public abstract ProviderEnum getProvider();
}