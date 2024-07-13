package com.shareduck.shareduck.common.request.argumentresolver;

import com.shareduck.shareduck.common.request.argumentresolver.helper.JwtResolverHelper;
import com.shareduck.shareduck.common.request.dto.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class JwtAuthResolver implements HandlerMethodArgumentResolver {
    private final JwtResolverHelper helper;

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return helper.findLoginUserId((HttpServletRequest) webRequest.getNativeRequest());
    }
}