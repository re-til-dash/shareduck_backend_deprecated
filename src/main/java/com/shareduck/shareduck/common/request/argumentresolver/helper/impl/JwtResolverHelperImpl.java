package com.shareduck.shareduck.common.request.argumentresolver.helper.impl;

import com.shareduck.shareduck.common.exception.BusinessLogicException;
import com.shareduck.shareduck.common.request.argumentresolver.helper.JwtResolverHelper;
import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.common.security.exception.AuthExceptionCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtResolverHelperImpl implements JwtResolverHelper {
    @Override
    public CurrentUser findLoginUserId(HttpServletRequest request) {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (auth instanceof String) {
            throw new BusinessLogicException(AuthExceptionCode.UNAUTHENTICATED);
        }
        Long principal = (Long) auth;
        return new CurrentUser(principal);
    }
}
