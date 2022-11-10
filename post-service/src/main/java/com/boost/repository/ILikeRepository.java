package com.boost.repository;

import com.boost.repository.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeRepository extends MongoRepository<Like,String> {
}