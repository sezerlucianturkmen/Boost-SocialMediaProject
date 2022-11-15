package com.boost.mapper;

import com.boost.dto.request.CreateCommentDto;
import com.boost.repository.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-10T22:15:49+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class ICommentMapperImpl implements ICommentMapper {

    @Override
    public Comment toComment(CreateCommentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.content( dto.getContent() );
        comment.userId( dto.getUserId() );
        comment.postId( dto.getPostId() );
        comment.username( dto.getUsername() );

        return comment.build();
    }
}
