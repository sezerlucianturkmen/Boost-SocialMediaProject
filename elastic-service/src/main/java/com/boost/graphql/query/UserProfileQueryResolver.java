package com.boost.graphql.query;

import com.boost.repository.entity.UserProfile;
import com.boost.service.UserProfileService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserProfileQueryResolver implements GraphQLQueryResolver {


    private final UserProfileService userProfileService;


    public Iterable<UserProfile> findAll(){

        return userProfileService.findAll();
    }


    public List<UserProfile> findAllContainingUsername(String username){

        return userProfileService.findAllContainingUsername(username);
    }

    public List<UserProfile> findAllByStatus(String status){

        return userProfileService.findAllByStatus(status);
    }

    public List<UserProfile> findAllContainingEmail(String email){

        return userProfileService.findAllContainingEmail(email);
    }
}