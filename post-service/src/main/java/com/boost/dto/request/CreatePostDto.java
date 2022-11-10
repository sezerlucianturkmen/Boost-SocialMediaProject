package com.boost.dto.request;
import com.boost.repository.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreatePostDto {

    String userId;
    String username;
    String title;
    String content;
    String postmediaurl;

}