package com.boost.repository;

import com.boost.repository.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IPostRepository extends MongoRepository<Post,String> {

    List<Post> findAllByUserId(String id);


}