package com.shareduck.shareduck.common.security.handler;

import com.shareduck.shareduck.common.utils.response.ErrorResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFailureCustomHandler implements AuthenticationFailureHandler {

    private final ErrorResponseUtils errorResponseUtils;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        log.error("# Authentication failed: {}", exception.getMessage());
        log.error("authentication ", exception);

        errorResponseUtils.sendErrorResponse(response);
    }
}