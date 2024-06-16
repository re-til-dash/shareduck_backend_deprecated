package com.shareduck.shareduck.common.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {
    HttpStatus getHttpStatus();

    String getMessage();
}
