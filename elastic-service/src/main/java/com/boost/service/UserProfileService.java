package com.boost.service;


import com.boost.repository.IUserProfileRepository;
import com.boost.repository.entity.UserProfile;
import com.boost.repository.enums.Status;
import com.boost.utility.JwtTokenManager;
import com.boost.utility.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository userProfileRepository;



    public UserProfileService(IUserProfileRepository userProfileRepository) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;

    }


    public List<UserProfile> findAllContainingUsername(String username) {


        return userProfileRepository.findByUsernameContainingIgnoreCase(username);
    }

    public List<UserProfile> findAllByStatus(String status) {

        return userProfileRepository.findAllByStatus(Status.valueOf(status));
    }

    public List<UserProfile> findAllContainingEmail(String email) {

        return userProfileRepository.findByEmailContainingIgnoreCase(email);
    }
}