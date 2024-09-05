package com.shareduck.shareduck.domain.user.persistence.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    USER("ROLE_USER");

    private String roll;
}
