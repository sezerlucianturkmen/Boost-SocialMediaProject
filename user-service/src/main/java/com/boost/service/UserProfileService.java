package com.boost.service;

import com.boost.dto.request.ActivateRequestDto;
import com.boost.dto.request.NewUserCreateDto;
import com.boost.dto.request.UpdateRequestDto;
import com.boost.dto.response.RoleResponseDto;
import com.boost.dto.response.UserProfileRedisResponseDto;
import com.boost.dto.response.UserProfileResponseDto;
import com.boost.exception.ErrorType;
import com.boost.exception.UserManagerException;
import com.boost.manager.IAuthManager;
import com.boost.manager.IElasticManager;
import com.boost.mapper.IUserMapper;
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

/**
 * 1-Status deðiþdiði zaman bizim active status cache temizlensin
 * 2-Userprofilecontrollerda bir endpoint
 * buda bize dýþarýdan girdiðimiz role göre bize user profilelarý donsün
 * ve bu metodu cachleyelim
 * bu metod ne zaman deðiþecek yani bu cache bir metodun içinde yeri geldiði zaman silelim
 *
 *
 *
 *
 *
 */
@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository userProfileRepository;
    private  final JwtTokenManager jwtTokenManager;
    @Autowired
    private CacheManager cacheManager;

    private final IAuthManager authManager;

    private  final IElasticManager elasticManager;

    public UserProfileService(IUserProfileRepository userProfileRepository,
                              JwtTokenManager jwtTokenManager,IAuthManager authManager,
                              IElasticManager elasticManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.jwtTokenManager=jwtTokenManager;
        this.authManager=authManager;
        this.elasticManager=elasticManager;
    }


    public UserProfile createUser(NewUserCreateDto dto){
        UserProfile userProfile=userProfileRepository.save(IUserMapper.INSTANCE.toUserProfile(dto));
        elasticManager.createUser(IUserMapper.INSTANCE.toUserProfileResponseDto(userProfile));
        return userProfile;
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
                cacheManager.getCache("findbyusername").evict(userProfileDb.get().getUsername().toUpperCase());
                userProfileDb.get().setEmail(dto.getEmail());
                userProfileDb.get().setAddress(dto.getAddress());
                userProfileDb.get().setAbout(dto.getAbout());
                userProfileDb.get().setName(dto.getName());
                userProfileDb.get().setUsername(dto.getUsername());
                userProfileDb.get().setPhone(dto.getPhone());
                userProfileDb.get().setPhone(dto.getPhoto());
                userProfileDb.get().setUpdated(System.currentTimeMillis());
                save(userProfileDb.get());
                elasticManager.update(IUserMapper.INSTANCE.toUserProfileResponseDto(userProfileDb.get()));
                return true;
            }else {throw new UserManagerException(ErrorType.USER_NOT_FOUND);}
        }else {throw new UserManagerException(ErrorType.INVALID_TOKEN);}
    }

    public boolean updateUserForRedis(UpdateRequestDto dto) {

        Optional<Long> authid=jwtTokenManager.getUserId(dto.getToken());
        if (authid.isPresent()){
            Optional<UserProfile> userProfileDb=userProfileRepository.findOptionalByAuthid(authid.get());
            if (userProfileDb.isPresent()){
                cacheManager.getCache("findbyusername").evict(userProfileDb.get().getUsername().toUpperCase());
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

    @Cacheable(value = "findbyusername",key = "#username.toUpperCase()")
    public UserProfileRedisResponseDto findByUsername(String username) {

        Optional<UserProfile> userProfile=userProfileRepository.findOptionalByUsernameEqualsIgnoreCase(username);

        if (userProfile.isPresent()){
            return IUserMapper.INSTANCE.toUserProfileRedisResponseDto(userProfile.get());
        }else {

            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
    }

    @Cacheable(value = "findactiveprofile")
    public List<UserProfile> findAllActiveProfile() {
        return  userProfileRepository.getActiveProfile();
    }

    @Cacheable(value = "findbyrole",key = "#roles.toUpperCase()")
    public List<RoleResponseDto> findByRole(String roles) {
        return authManager.findAllByRole(roles).getBody();
    }
}