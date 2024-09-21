package com.shareduck.shareduck.domain.user.web.controller.doc;

import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.domain.user.web.dto.request.PasswordUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.request.PatchUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.request.PostUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.response.GetUserResponse;
import com.shareduck.shareduck.domain.user.web.dto.response.GetUserSimpleResponse;
import com.shareduck.shareduck.domain.user.web.dto.response.UserIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "User Domain API")
public interface UserControllerDoc {

    @Operation(summary = "회원 가입 [ALL]", description = "")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201")
        }
    )
    UserIdResponse post(PostUserRequest dto);

    @Operation(summary = "사용자 삭제 [USER]", description = "")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "204")
        }
    )
    void delete(@Parameter(hidden = true) CurrentUser user);

    @Operation(summary = "사용자 수정 [USER]", description = "")
    UserIdResponse patch(@RequestBody PatchUserRequest dto,
        @Parameter(hidden = true) CurrentUser user);

    @Operation(summary = "비밀번호 변경 [USER]", description = "")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "204")
        }
    )
    UserIdResponse changePassword(@RequestBody PasswordUserRequest dto,
        @Parameter(hidden = true) CurrentUser user);


    @Operation(summary = "로그인 후 회원 정보 조회 [USER]", description = "")
    GetUserResponse get(@Parameter(hidden = true) CurrentUser user);

    @Operation(summary = "회원 정보 조회 [ALL]", description = "")
    @Parameters(
        value = {
            @Parameter(name = "idx" ,description = "사용자 고유 ID", in = ParameterIn.PATH)
        }
    )
    GetUserResponse get(@PathVariable("idx") String idx);

    @Operation(summary = "회원 리스트 조회 [ALL]", description = "")
    @Parameters(
        value = {
            @Parameter(name = "page" ,description = "몇 번째 페이지 정보인가 <br> 0부터 시작", in = ParameterIn.QUERY),
            @Parameter(name = "size" ,description = "한 페이지에 전달되는 데이터 수", in = ParameterIn.QUERY),
            @Parameter(name = "sort" ,description = "정렬 방법", in = ParameterIn.QUERY)
        }
    )
    Page<GetUserSimpleResponse> get(@Parameter(hidden = true) Pageable pageable);
}
