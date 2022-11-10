package com.boost.service;

import com.boost.repository.IDislikeRepository;
import com.boost.repository.IPostRepository;
import com.boost.repository.entity.Dislike;
import com.boost.repository.entity.Post;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class DislikeService extends ServiceManager<Dislike,String> {
    private final IDislikeRepository dislikeRepository;

    public DislikeService(IDislikeRepository dislikeRepository) {
        super(dislikeRepository);
        this.dislikeRepository = dislikeRepository;
    }
}
