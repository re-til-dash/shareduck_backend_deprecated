package com.shareduck.shareduck.common.request.argumentresolver.helper;

import com.shareduck.shareduck.common.request.dto.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;

public interface JwtResolverHelper {
    CurrentUser findLoginUserId(HttpServletRequest request);

}
