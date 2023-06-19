package com.example.springbootmongodb.repository;

import com.example.springbootmongodb.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/6/16 13:00
 */
public interface UserRepository extends MongoRepository<User,String> {

}
