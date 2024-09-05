package com.shareduck.shareduck.domain.user.web.controller.doc;

import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.domain.user.web.dto.request.PasswordUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.request.PatchUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.request.PostUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.response.GetUserResponse;
import com.shareduck.shareduck.domain.user.web.dto.response.UserIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "User Domain API")
public interface UserControllerDoc {

    @Operation(summary = "회원 가입 (ALL)", description = "")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201")
        }
    )
    UserIdResponse post(PostUserRequest dto);

    @Operation(summary = "사용자 삭제 (USER)", description = "")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "204")
        }
    )
    void delete(@Parameter(hidden = true) CurrentUser user);

    @Operation(summary = "사용자 수정 (USER)", description = "")
    UserIdResponse patch(@RequestBody PatchUserRequest dto,
        @Parameter(hidden = true) CurrentUser user);

    @Operation(summary = "비밀번호 변경 (USER)", description = "")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "204")
        }
    )
    UserIdResponse changePassword(@RequestBody PasswordUserRequest dto,
        @Parameter(hidden = true) CurrentUser user);
}
