package com.shareduck.shareduck.domain.user.business.exception;

import com.shareduck.shareduck.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserExceptionCode implements ExceptionCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원 정보를 찾을 수 없습니다.","USER-01"),
    USER_ALREADY_EXIST(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.", "USER-02"),
    NOT_SUPPORT_PROVIDER(HttpStatus.BAD_REQUEST, "소셜 로그인 회원은 사용할 수 없습니다.", "USER-03");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;
}
