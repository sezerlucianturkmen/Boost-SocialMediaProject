package com.boost.service;


import com.boost.dto.request.NewUserCreateDto;
import com.boost.dto.request.UpdateRequestDto;
import com.boost.exception.ErrorType;
import com.boost.exception.UserManagerException;
import com.boost.mapper.IUserMapper;
import com.boost.repository.IUserProfileRepository;
import com.boost.repository.entity.UserProfile;
import com.boost.repository.enums.Status;
import com.boost.utility.JwtTokenManager;
import com.boost.utility.ServiceManager;
import com.boost.dto.request.ActivateRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository userProfileRepository;
    private  final JwtTokenManager jwtTokenManager;

    public UserProfileService(IUserProfileRepository userProfileRepository,JwtTokenManager jwtTokenManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.jwtTokenManager=jwtTokenManager;
    }


    public UserProfile createUser(NewUserCreateDto dto){
        return userProfileRepository.save(IUserMapper.INSTANCE.toUserProfile(dto));
    }

    public boolean activateStatus(ActivateRequestDto dto) {
        Optional<UserProfile> userProfile=userProfileRepository.findOptionalByAuthid(dto.getId());
        if (userProfile.isEmpty()){
            throw  new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(Status.ACTIVE);
        save(userProfile.get());
        return  true;

    }

    public boolean updateUser(UpdateRequestDto dto) {

        Optional<Long> authid=jwtTokenManager.getUserId(dto.getToken());
        if (authid.isPresent()){
            Optional<UserProfile> userProfileDb=userProfileRepository.findOptionalByAuthid(authid.get());
            if (userProfileDb.isPresent()){

                userProfileDb.get().setEmail(dto.getEmail());
                userProfileDb.get().setAddress(dto.getAddress());
                userProfileDb.get().setAbout(dto.getAbout());
                userProfileDb.get().setName(dto.getName());
                userProfileDb.get().setUsername(dto.getUsername());
                userProfileDb.get().setPhone(dto.getPhone());
                userProfileDb.get().setPhone(dto.getPhoto());
                save(userProfileDb.get());
                return true;
            }else {
                throw new UserManagerException(ErrorType.USER_NOT_FOUND);
            }
        }else {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public Boolean activateStatus(Long authid) {

        Optional<UserProfile> userProfile=userProfileRepository.findOptionalByAuthid(authid);
        if (userProfile.isEmpty()){
            throw  new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(Status.ACTIVE);
        save(userProfile.get());
        return  true;
    }
}