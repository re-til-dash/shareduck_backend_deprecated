package com.shareduck.shareduck.common.security.vo;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter
public class Principal extends User {
    private final Long id;

    public Principal(UserEntity user) {
        super(
                user.getEmail(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().getRoll()));
        this.id = user.getId();
    }

    public Principal(Claims claims) {
        super(
                claims.getSubject(),
                "",
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        claims.get("authorities", String.class)));
        this.id = claims.get("id", Long.class);
    }
}
