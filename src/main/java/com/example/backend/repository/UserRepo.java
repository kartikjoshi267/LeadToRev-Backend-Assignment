package com.example.backend.repository;

import com.example.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    @Query("{ 'email' : { $regex: ?0, $options: 'i' } }")
    User findByEmail(String email);
}
