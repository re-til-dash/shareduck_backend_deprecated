package com.shareduck.shareduck.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.shareduck.shareduck.common.utils.TimeUtils;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;

@JsonPropertyOrder({
    "timestamp",
    "data"
})
@Builder(access = AccessLevel.PRIVATE)
public record ApiResponse<T>(
    @JsonFormat(pattern = TimeUtils.UTC_TIME_FORMAT)
    LocalDateTime timestamp,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data
) {
    public ApiResponse(T data) {
        this(TimeUtils.getCurrent(), data);
    }
}

