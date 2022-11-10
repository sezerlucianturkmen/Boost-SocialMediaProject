package com.boost.service;


import com.boost.dto.request.DeleteFollowDto;
import com.boost.dto.request.FollowCreateDto;
import com.boost.dto.response.FollowListResponseDto;
import com.boost.exception.ErrorType;
import com.boost.exception.UserManagerException;
import com.boost.mapper.IFollowMapper;
import com.boost.repository.IFollowRepository;
import com.boost.repository.entity.Follow;
import com.boost.repository.entity.UserProfile;
import com.boost.utility.JwtTokenManager;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FollowService extends ServiceManager<Follow, String> {


    private final IFollowRepository followRepository;
    private final UserProfileService userProfileService;
    private final JwtTokenManager jwtTokenManager;

    public FollowService(IFollowRepository followRepository, UserProfileService userProfileService,JwtTokenManager jwtTokenManager) {
        super(followRepository);
        this.followRepository = followRepository;
        this.userProfileService = userProfileService;
        this.jwtTokenManager=jwtTokenManager;
    }

    public Follow create(FollowCreateDto dto) {

        Optional<UserProfile> userProfile = userProfileService.findById(dto.getUserId());
        Optional<UserProfile> followUser = userProfileService.findById(dto.getFollowId());
        Follow follow;
        if (userProfile.isPresent() && followUser.isPresent()) {
            follow = save(IFollowMapper.INSTANCE.toFollow(dto));
            userProfile.get().getFollows().add(dto.getFollowId());
            followUser.get().getFollowers().add(dto.getUserId());
            userProfileService.save(userProfile.get());
            userProfileService.save(followUser.get());

        } else {

            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }

        return follow;
    }

    public Boolean deleteFollow(DeleteFollowDto dto) {
        Optional<Long> authId = jwtTokenManager.getUserId(dto.getToken());
        if (authId.isPresent()) {
            Optional<UserProfile> userProfile = userProfileService.findByAuthId(authId.get());
            Optional<UserProfile> followUser = userProfileService.findById(dto.getFollowId());
            if (userProfile.isPresent() && followUser.isPresent()) {
                Optional<Follow> follow = followRepository.findOptionalByFollowIdAndUserId(dto.getFollowId(), userProfile.get().getId());
                if (follow.isPresent()) {
                    userProfile.get().getFollows().remove(followUser.get().getId());
                    userProfileService.save(userProfile.get());
                    followUser.get().getFollowers().remove(userProfile.get().getId());
                    userProfileService.save(followUser.get());
                    delete(follow.get());
                    return true;
                } else {
                    throw new UserManagerException(ErrorType.FOLLOW_NOT_FOUND);
                }


            } else {
                throw new UserManagerException(ErrorType.USER_NOT_FOUND);
            }

        } else {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public List<UserProfile> findFollowById(String token) {
        Optional<Long> authId = jwtTokenManager.getUserId(token);
        if (authId.isPresent()) {
            List<String> followsId = userProfileService.findByAuthId(authId.get()).get().getFollows();

            return followsId.stream().map(id -> {
                return userProfileService.findById(id).get();
            }).collect(Collectors.toList());
        } else {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
    }

}