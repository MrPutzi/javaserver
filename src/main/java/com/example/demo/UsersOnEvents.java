package com.example.demo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UsersOnEvents")
    public class UsersOnEvents {
        @Id
        private ObjectId id;
        private ObjectId userId;
        private ObjectId eventId;


        public UsersOnEvents(ObjectId userId, ObjectId eventId) {
            this.userId = userId;
            this.eventId = eventId;
        }

        public UsersOnEvents(ObjectId id, ObjectId userId, ObjectId eventId) {
            this.id = id;
            this.userId = userId;
            this.eventId = eventId;
        }

    public UsersOnEvents() {
            }

    public UsersOnEvents(ObjectId id) {
        this.id = id;
        }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getEventId() {
        return eventId;
    }

    public void setEventId(ObjectId eventId) {
        this.eventId = eventId;
    }
}
