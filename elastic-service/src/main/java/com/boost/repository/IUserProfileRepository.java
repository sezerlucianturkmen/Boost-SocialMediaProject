package com.boost.repository;

import com.boost.repository.entity.UserProfile;
import com.boost.repository.enums.Status;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.util.List;
import java.util.Optional;

public interface IUserProfileRepository extends ElasticsearchRepository<UserProfile ,Long> {



    Optional<UserProfile> findOptionalByAuthid(Long id);

    Optional<UserProfile> findOptionalByUsername(String username);
    Optional<UserProfile> findOptionalByUsernameEqualsIgnoreCase(String username);
    List<UserProfile> findAllByStatus(Status status);
    List<UserProfile> findByUsernameContainingIgnoreCase(String username);
    List<UserProfile> findByEmailContainingIgnoreCase(String email);
}