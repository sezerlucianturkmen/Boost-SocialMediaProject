package com.boost.repository;
import com.boost.repository.entity.Follow;
import com.boost.repository.entity.Follower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowerRepository extends MongoRepository<Follower, String> {


}