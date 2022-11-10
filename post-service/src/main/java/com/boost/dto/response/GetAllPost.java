package com.boost.dto.response;

import com.boost.repository.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllPost {

    String id;
    String userId;
    String username;
    String title;
    String content;
    String postMediaUrl;
    int like;
    int dislike;
    long sharedTime;
    List<Comment> commentList;

}