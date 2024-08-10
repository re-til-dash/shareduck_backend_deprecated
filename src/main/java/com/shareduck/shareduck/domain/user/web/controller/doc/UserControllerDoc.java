package com.shareduck.shareduck.domain.user.web.controller.doc;

import com.shareduck.shareduck.domain.user.web.dto.request.PostUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.response.PostUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "User Domain API")
public interface UserControllerDoc {

    @Operation(summary = "시스템 상태 조회 - 전체 (USER)", description = "")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201")
        }
    )
    PostUserResponse post(PostUserRequest dto);
}
