package com.boost.mapper;


import com.boost.dto.request.CreatePostDto;
import com.boost.dto.response.GetAllPost;
import com.boost.repository.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IPostMapper {


    IPostMapper INSTANCE = Mappers.getMapper(IPostMapper.class);

    Post toPost(final CreatePostDto dto);

    List<GetAllPost> toGetAllPosts(final List<Post> posts);

    GetAllPost toGetAllPost(final Post post);

}