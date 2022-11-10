package com.boost.mapper;
import com.boost.dto.request.DeleteFollowDto;
import com.boost.dto.request.FollowCreateDto;
import com.boost.repository.entity.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IFollowMapper {


    IFollowMapper INSTANCE = Mappers.getMapper(IFollowMapper.class);


    Follow toFollow(final FollowCreateDto dto);
    Follow toFollow(final DeleteFollowDto dto);

}