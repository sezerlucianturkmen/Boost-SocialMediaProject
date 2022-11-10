package com.boost.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Comment {
    @Id
    String id;
    String content;
    String userId;
    String postId;
    String username;
    @Builder.Default
    long sharedTime=System.currentTimeMillis();
    int like;
    int dislike;




}