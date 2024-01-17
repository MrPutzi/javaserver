package com.example.demo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {


    Mono<Object> findByUsernameAndPassword(String username, String password);


    Mono<Void> deleteByUsername(String username);



}