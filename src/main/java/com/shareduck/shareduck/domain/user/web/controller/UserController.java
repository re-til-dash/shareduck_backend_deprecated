package com.shareduck.shareduck.domain.user.web.controller;

import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.domain.user.business.facade.UserFacade;
import com.shareduck.shareduck.domain.user.web.controller.doc.UserControllerDoc;
import com.shareduck.shareduck.domain.user.web.dto.request.PostUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.response.PostUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserControllerDoc {
    private final UserFacade facade;

    @PostMapping
    public PostUserResponse post(@RequestBody PostUserRequest dto){
        return facade.post(dto);
    }

    @DeleteMapping
    public void delete(CurrentUser user){
        facade.delete(user.getUserId());
    }
}