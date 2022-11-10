package com.boost.service;

import com.boost.repository.IFollowerRepository;
import com.boost.repository.entity.Follower;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class FollowerService extends ServiceManager<Follower, String> {
    private final IFollowerRepository followerRepository;

    public FollowerService(IFollowerRepository followerRepository) {
        super(followerRepository);
        this.followerRepository = followerRepository;
    }
}