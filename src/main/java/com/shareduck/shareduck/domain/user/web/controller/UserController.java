package com.shareduck.shareduck.domain.user.web.controller;

import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.domain.user.business.facade.UserFacade;
import com.shareduck.shareduck.domain.user.web.controller.doc.UserControllerDoc;
import com.shareduck.shareduck.domain.user.web.dto.request.PasswordUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.request.PatchUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.request.PostUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.response.GetUserResponse;
import com.shareduck.shareduck.domain.user.web.dto.response.UserIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public UserIdResponse post(@RequestBody PostUserRequest dto) {
        return facade.post(dto);
    }

    @DeleteMapping
    public void delete(CurrentUser user) {
        facade.delete(user.getUserId());
    }

    @PatchMapping
    public UserIdResponse patch(@RequestBody PatchUserRequest dto, CurrentUser user) {
        return facade.patch(dto, user.getUserId());
    }

    @PatchMapping("/password")
    public UserIdResponse changePassword(@RequestBody PasswordUserRequest dto, CurrentUser user) {
        return facade.changePassword(user.getUserId(), dto.password());
    }

    //login user
    @GetMapping
    public GetUserResponse get(CurrentUser user) {
        return facade.get(user.getUserId());
    }

    @GetMapping("/{idx}")
    public GetUserResponse get(@PathVariable("idx") String idx) {
        return facade.get(idx);
    }


}
