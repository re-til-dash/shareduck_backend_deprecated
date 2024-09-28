package com.shareduck.shareduck.domain.board.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class PostUpdateReq {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private List<String> hashTags;

    @NotNull
    private Map<String, Object> properties;

    private String thumbnailPath;

}
