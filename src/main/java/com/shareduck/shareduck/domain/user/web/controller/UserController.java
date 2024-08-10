package com.shareduck.shareduck.domain.user.web.controller;

import com.shareduck.shareduck.domain.user.business.facade.UserFacade;
import com.shareduck.shareduck.domain.user.web.controller.doc.UserControllerDoc;
import com.shareduck.shareduck.domain.user.web.dto.request.PostUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.response.PostUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserControllerDoc {
    private final UserFacade service;

    @PostMapping
    public PostUserResponse post(@RequestBody PostUserRequest dto){
        return service.post(dto);
    }
}
