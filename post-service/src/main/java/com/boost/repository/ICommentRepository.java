package com.boost.repository;

import com.boost.repository.entity.Comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICommentRepository extends MongoRepository<Comment,String> {

    Optional<List<Comment>> findAllOptionalByPostId(String id);
}