package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {

    @Id
    private String id;
    private String username;
    private String password;


    public static class UserId {
        public String username;
        public String password;
        public String $oid;

        public UserId(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public UserId(User.UserId userId, String username, String password) {
            this.username = username;
            this.password = password;
        }

        public UserId() {

        }
    }


    @Override
    public String toString() {
        return "User{" +
                ", username=" + username +
                ", password=" + password +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
    	return username;
    }

    public String getName() {
    	return username;
    }

    public void setUsername(String username) {
    	this.username = username;
    }

    public String getPassword() {
    	return password;
    }

public void setPassword(String password) {
    	this.password = password;
    }

}
// Compare this snippet from src/main/java/com/example/demo/EventController.java:
