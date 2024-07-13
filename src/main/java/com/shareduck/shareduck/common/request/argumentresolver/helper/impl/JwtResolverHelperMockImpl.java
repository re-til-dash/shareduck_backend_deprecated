package com.shareduck.shareduck.common.request.argumentresolver.helper.impl;

import com.shareduck.shareduck.common.request.argumentresolver.helper.JwtResolverHelper;
import com.shareduck.shareduck.common.request.dto.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtResolverHelperMockImpl implements JwtResolverHelper {

    @Override
    public CurrentUser findLoginUserId(HttpServletRequest request) {
        return new CurrentUser(1L);
    }
}
