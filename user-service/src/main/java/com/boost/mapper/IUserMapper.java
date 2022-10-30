package com.boost.mapper;

import com.boost.dto.request.NewUserCreateDto;
import com.boost.dto.request.UpdateRequestDto;
import com.boost.dto.response.UserProfileRedisResponseDto;
import com.boost.dto.response.UserProfileResponseDto;
import com.boost.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IUserMapper {


    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);


    UserProfile toUserProfile(final NewUserCreateDto dto);


    UserProfile toUserProfile(final UpdateRequestDto dto);

    UserProfileRedisResponseDto toUserProfileRedisResponseDto(final UserProfile userProfile);

    UserProfileResponseDto toUserProfileResponseDto(final  UserProfile userProfile);
    List<UserProfileResponseDto> toUserProfileResponseDtoList(final  List<UserProfile> userProfile);

}