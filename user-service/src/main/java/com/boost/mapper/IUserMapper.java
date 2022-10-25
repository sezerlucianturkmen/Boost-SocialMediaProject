package com.boost.mapper;

import com.boost.dto.request.NewUserCreateDto;
import com.boost.dto.request.UpdateRequestDto;
import com.boost.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserMapper {

    IUserMapper INSTANCE=Mappers.getMapper(IUserMapper.class);

    UserProfile toUserProfile(final NewUserCreateDto dto);


    UserProfile toUserProfile(final UpdateRequestDto dto);

}
