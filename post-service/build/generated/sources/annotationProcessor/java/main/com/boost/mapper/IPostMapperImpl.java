package com.boost.mapper;

import com.boost.dto.request.CreatePostDto;
import com.boost.dto.response.GetAllPost;
import com.boost.repository.entity.Post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-10T22:15:50+0300",
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

    @Override
    public List<GetAllPost> toGetAllPosts(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<GetAllPost> list = new ArrayList<GetAllPost>( posts.size() );
        for ( Post post : posts ) {
            list.add( toGetAllPost( post ) );
        }

        return list;
    }

    @Override
    public GetAllPost toGetAllPost(Post post) {
        if ( post == null ) {
            return null;
        }

        GetAllPost.GetAllPostBuilder getAllPost = GetAllPost.builder();

        getAllPost.id( post.getId() );
        getAllPost.userId( post.getUserId() );
        getAllPost.username( post.getUsername() );
        getAllPost.title( post.getTitle() );
        getAllPost.content( post.getContent() );
        getAllPost.postMediaUrl( post.getPostMediaUrl() );
        getAllPost.like( post.getLike() );
        getAllPost.dislike( post.getDislike() );
        getAllPost.sharedTime( post.getSharedTime() );

        return getAllPost.build();
    }
}
