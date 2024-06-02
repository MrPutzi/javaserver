package com.example.demo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.concurrent.ForkJoinPool;

public interface UsersOnEventsRepository extends ReactiveMongoRepository<UsersOnEvents, String> {

    UsersOnEvents findByUserId(String userId);
    UsersOnEvents findByEventId(String eventId);
    UsersOnEvents findByUserIdAndEventId(String userId, String eventId);

        boolean existsByEventIdAndUserId(String eventId, String userId);

    ForkJoinPool.ManagedBlocker deleteByUserIdAndEventId(ObjectId objectId, ObjectId objectId1);

}



