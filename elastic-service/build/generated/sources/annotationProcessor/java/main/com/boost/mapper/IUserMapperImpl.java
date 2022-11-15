package com.boost.mapper;

import com.boost.dto.response.UserProfileResponseDto;
import com.boost.graphql.model.UserProfileInput;
import com.boost.repository.entity.UserProfile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-10T22:21:49+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserProfile toUserProfile(UserProfileResponseDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder userProfile = UserProfile.builder();

        userProfile.id( dto.getId() );
        userProfile.authid( dto.getAuthid() );
        userProfile.username( dto.getUsername() );
        userProfile.name( dto.getName() );
        userProfile.email( dto.getEmail() );
        userProfile.phone( dto.getPhone() );
        userProfile.photo( dto.getPhoto() );
        userProfile.address( dto.getAddress() );
        userProfile.about( dto.getAbout() );
        userProfile.created( dto.getCreated() );
        userProfile.updated( dto.getUpdated() );
        userProfile.status( dto.getStatus() );

        return userProfile.build();
    }

    @Override
    public List<UserProfileResponseDto> toUserProfileResponseDtoList(List<UserProfile> userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        List<UserProfileResponseDto> list = new ArrayList<UserProfileResponseDto>( userProfile.size() );
        for ( UserProfile userProfile1 : userProfile ) {
            list.add( userProfileToUserProfileResponseDto( userProfile1 ) );
        }

        return list;
    }

    @Override
    public UserProfile toUserProfile(UserProfileInput userProfileInput) {
        if ( userProfileInput == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder userProfile = UserProfile.builder();

        userProfile.username( userProfileInput.getUsername() );
        userProfile.email( userProfileInput.getEmail() );

        return userProfile.build();
    }

    protected UserProfileResponseDto userProfileToUserProfileResponseDto(UserProfile userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        UserProfileResponseDto.UserProfileResponseDtoBuilder userProfileResponseDto = UserProfileResponseDto.builder();

        userProfileResponseDto.id( userProfile.getId() );
        userProfileResponseDto.authid( userProfile.getAuthid() );
        userProfileResponseDto.username( userProfile.getUsername() );
        userProfileResponseDto.name( userProfile.getName() );
        userProfileResponseDto.email( userProfile.getEmail() );
        userProfileResponseDto.phone( userProfile.getPhone() );
        userProfileResponseDto.photo( userProfile.getPhoto() );
        userProfileResponseDto.address( userProfile.getAddress() );
        userProfileResponseDto.about( userProfile.getAbout() );
        userProfileResponseDto.created( userProfile.getCreated() );
        userProfileResponseDto.updated( userProfile.getUpdated() );
        userProfileResponseDto.status( userProfile.getStatus() );

        return userProfileResponseDto.build();
    }
}
