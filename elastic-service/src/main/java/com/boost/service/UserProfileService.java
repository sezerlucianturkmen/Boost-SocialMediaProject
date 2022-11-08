package com.boost.service;


import com.boost.exception.ElasticManagerException;
import com.boost.exception.ErrorType;
import com.boost.rabbitmq.model.UpdateUsernameEmail;
import com.boost.repository.IUserProfileRepository;
import com.boost.repository.entity.UserProfile;
import com.boost.repository.enums.Status;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 1-Status deðiþdiði zaman bizim active status cache temizlensin
 * 2-Userprofilecontrollerda bir endpoint
 * buda bize dýþarýdan girdiðimiz role göre bize user profilelarý donsün
 * ve bu metodu cachleyelim
 * bu metod ne zaman deðiþecek yani bu cache bir metodun içinde yeri geldiði zaman silelim
 */
@Service
public class UserProfileService extends ServiceManager<UserProfile, Long> {

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

    @Transactional
    public boolean deleteUser(Long id) {

        Optional<UserProfile> userProfile = userProfileRepository.findOptionalByAuthid(id);

        if (userProfile.isPresent()) {
            userProfile.get().setStatus(Status.DELETED);
            save(userProfile.get());
            return true;
        } else {
            throw new ElasticManagerException(ErrorType.USER_NOT_FOUND);
        }
    }

    public boolean updateUser(UpdateUsernameEmail model) {

        Optional<UserProfile> auth = userProfileRepository.findOptionalByAuthid(model.getAuthid());

        if (auth.isPresent()) {
            auth.get().setEmail(model.getEmail());
            auth.get().setUsername(model.getUsername());
            save(auth.get());
            return true;
        } else {
            throw new ElasticManagerException(ErrorType.USER_NOT_FOUND);
        }
    }
}