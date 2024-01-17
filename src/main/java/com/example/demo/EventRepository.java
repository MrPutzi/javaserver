package com.example.demo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventRepository extends ReactiveMongoRepository<Event, String> {

    Flux<Event> findByName(String name);
    Mono<Event> findById(String id);


    Mono<User> findByName(String username, String password);

    Mono<Event> findTopByOrderByIdDesc();

}
