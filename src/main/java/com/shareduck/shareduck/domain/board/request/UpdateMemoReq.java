package com.shareduck.shareduck.domain.board.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Deprecated
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class UpdateMemoReq {

    @NotBlank
    private String content;

    public static UpdateMemoReq testConstructor(String content) {
        return new UpdateMemoReq(content);
    }
}
