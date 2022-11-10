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
public class Post {
    @Id
    String id;
    String title;
    String content;
    String userId;
    String username;
    String postMediaUrl;
    int like;
    int dislike;
    @Builder.Default
    long  sharedTime=System.currentTimeMillis();





}