package com.boost.mapper;

import com.boost.dto.request.CreatePostDto;
import com.boost.repository.entity.Post;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-09T11:06:02+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class IPostMapperImpl implements IPostMapper {

    @Override
    public Post toPost(CreatePostDto dto) {
        if ( dto == null ) {
            return null;
        }

        Post.PostBuilder post = Post.builder();

        post.title( dto.getTitle() );
        post.content( dto.getContent() );
        post.userId( dto.getUserId() );
        post.username( dto.getUsername() );

        return post.build();
    }
}
