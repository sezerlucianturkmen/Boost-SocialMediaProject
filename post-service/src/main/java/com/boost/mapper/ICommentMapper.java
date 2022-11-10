package com.boost.mapper;


import com.boost.dto.request.CreateCommentDto;
import com.boost.repository.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ICommentMapper {


    ICommentMapper INSTANCE = Mappers.getMapper(ICommentMapper.class);

    Comment toComment(final CreateCommentDto dto);

}