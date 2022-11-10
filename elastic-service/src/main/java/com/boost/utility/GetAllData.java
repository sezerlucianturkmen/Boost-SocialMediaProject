package com.boost.utility;


import com.boost.dto.response.UserProfileResponseDto;
import com.boost.manager.IUserManager;
import com.boost.mapper.IUserMapper;
import com.boost.repository.entity.UserProfile;
import com.boost.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllData {


    private  final UserProfileService userProfileService;

    private  final IUserManager userManager;

    //@PostConstruct
    public void init(){

        List<UserProfileResponseDto> userProfileList= userManager.findAll().getBody();


        userProfileService.saveAll(userProfileList.stream().
                map(dto-> IUserMapper.INSTANCE.toUserProfile(dto))
                .collect(Collectors.toList()));

    }

}