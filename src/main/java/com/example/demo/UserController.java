package com.example.demo;


import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    private MongoCollection<Document> eventCollection;
    private ReactiveMongoTemplate reactiveMongoTemplate;
    private  TokenManagement tokenManagement;

    public UserController(UserRepository userRepository, EventRepository eventRepository, ReactiveMongoTemplate reactiveMongoTemplate, TokenManagement tokenManagement) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.tokenManagement = tokenManagement;
    }
//    /*       LOGIN LOGOUT HANDLING         */


    @CrossOrigin
    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
                .map(u -> ResponseEntity.ok(tokenManagement.generateToken()))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @CrossOrigin
    @PostMapping("/logout")
    public Mono<ResponseEntity<String>> logout(@RequestBody User user) {
        return userRepository.deleteByUsername(user.getUsername())
                .map(u -> ResponseEntity.ok("User logged out successfully"))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
