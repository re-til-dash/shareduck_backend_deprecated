package com.shareduck.shareduck.common.security.vo;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import io.jsonwebtoken.Claims;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

@Getter
public class Principal extends DefaultOAuth2User implements UserDetails {

    private Long id;
    private String email;
    private String password;

    private Principal(UserEntity user, Map<String, Object> attributes, String nameAttributeKey) {
        super(AuthorityUtils.createAuthorityList(user.getRole().getRoll()), attributes,
            nameAttributeKey);
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.id = user.getId();
    }

    private Principal(Claims claims, Map<String, Object> attributes, String nameAttributeKey) {
        super(AuthorityUtils.commaSeparatedStringToAuthorityList(
                claims.get("authorities", String.class)),
            attributes,
            nameAttributeKey);
        this.email = claims.getSubject();
        this.password = "";
        this.id = claims.get("id", Long.class);
    }

    public static Principal jwt(UserEntity user) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("JWT", user.getEmail());
        return new Principal(user, attributes, "JWT");
    }

    public static Principal token(Claims claims) {
        return new Principal(claims, null, null);
    }

    public static Principal oauth(UserEntity user, Map<String, Object> attributes,
        String nameAttributeKey) {
        return new Principal(user, attributes, nameAttributeKey);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
