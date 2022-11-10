package com.boost.service;

import com.boost.repository.ILikeRepository;
import com.boost.repository.IPostRepository;
import com.boost.repository.entity.Like;
import com.boost.repository.entity.Post;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class LikeService extends ServiceManager<Like,String> {
    private final ILikeRepository likeRepository;

    public LikeService(ILikeRepository likeRepository) {
        super(likeRepository);
        this.likeRepository = likeRepository;
    }
}
