package com.boost.repository;
import com.boost.repository.entity.UserProfile;
import com.boost.repository.enums.Status;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IUserProfileRepository extends MongoRepository<UserProfile,String> {
    Optional<UserProfile> findOptionalByAuthid(Long id);

    Optional<UserProfile> findOptionalByUsername(String username);

    Optional<UserProfile> findOptionalByUsernameEqualsIgnoreCase(String username);


    List<UserProfile> findAllByStatus(Status status);

    @Query("select u from UserProfile as u  where u.status='ACTIVE'")
    List<UserProfile> getActiveProfile();
}
