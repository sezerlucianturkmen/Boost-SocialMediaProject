package com.boost.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateCommentDto {
    @NotNull
    String content;
    @NotNull
    String userId;
    @NotNull
    String postId;
    String username;
}
