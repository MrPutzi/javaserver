package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Mono;
@CrossOrigin
public interface EventRepository extends ReactiveMongoRepository<Event, String> {

//    Mono<Event> findByName(String name);
//    Mono<Object> findById(int id);
//    Mono<Object> deleteById(int id);
//    Mono<Object> deleteByName(String name);
//
//
//    Mono<User> findByName(String username, String password);


}
