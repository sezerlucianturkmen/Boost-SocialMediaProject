package com.boost.repository;

import com.boost.repository.entity.Dislike;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDislikeRepository extends MongoRepository<Dislike,String> {
}