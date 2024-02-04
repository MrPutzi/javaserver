package com.example.demo;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MongoOperations mongoOperations;
    private final MongoCollection<Document> eventCollection;

    @Autowired
    private MongoTemplate mongoTemplate;

    private TokenManagement tokenManagement;

    public Controller(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("MeetHub");
        eventCollection = database.getCollection("Events");
    }


    @CrossOrigin
    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
                .map(u -> ResponseEntity.ok(tokenManagement.generateToken()))  //token
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }


    @CrossOrigin
    @PostMapping("/logout")
    public Mono<ResponseEntity<String>> logout(@RequestBody User user) {
        return userRepository.deleteByUsername(user.getUsername())
                .map(u -> ResponseEntity.ok("User logged out successfully"))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
//    @CrossOrigin
//    @GetMapping("/getevents")
//    public List<Event> getAllEvents() {
//        return (List<Event>) eventRepository.findAll();
//    }


//    @CrossOrigin
//    @GetMapping("/getevents")
//    public List<Event> getAllEvents() {
//        // Retrieve all documents from MongoDB
//        List<Document> documents = mongoTemplate.findAll(Document.class, "Events");
//
//        // Convert MongoDB documents to your Event class
//        return documents.stream()
//                .map(document -> {
//                    // You need to create a method to convert Document to your Event class
//                    return convertDocumentToEvent(document);
//                })
//                .collect(Collectors.toList());
//    }
//
//    // Create a method to convert Document to your Event class




    @CrossOrigin
    @GetMapping("/getevents")
    public List<Event> getAllEvents() {
        List<Document> documents = eventCollection.find().into(new ArrayList<>());
        return documents.stream()
                .map(document -> {
                    return convertDocumentToEvent(document);
                })
                .collect(Collectors.toList());
    }

    private Event convertDocumentToEvent(Document document) {
        return new Event(
                document.getString("name"),
                document.getString("date"),
                document.getString("location"),
                document.getString("description")
        );
    }

    @CrossOrigin
    @PostMapping("/addevent")
    public Mono<ResponseEntity<String>> addEvent(@RequestBody Event event) {
        return eventRepository.save(event)
                .map(e -> ResponseEntity.ok("Event added successfully"))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }


    @CrossOrigin
    @GetMapping("/getcontacts")
    public List<Contact> getAllContacts() {
        List<Document> documents = mongoTemplate.findAll(Document.class, "Contacts");
        return documents.stream()
                .map(document -> {
                    return convertDocumentToContact(document);
                })
                .collect(Collectors.toList());
    }


    private Contact convertDocumentToContact(Document document) {
        Contact.ContactId contactId = new Contact.ContactId();
        contactId.$oid = document.getObjectId("_id").toHexString();
        return new Contact(
                document.getString("name"),
                document.getString("contact")

        );


    }

    @CrossOrigin
    @PostMapping("/addcontact")
    public Mono<ResponseEntity<String>> addContact(@RequestBody User user) {
        return userRepository.save(user)
                .map(u -> ResponseEntity.ok("Contact added successfully"))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @CrossOrigin
    @PostMapping("/deletecontact/"+ "{username}")
    public Mono<ResponseEntity<String>> deleteContact(@PathVariable String username) {
        return userRepository.deleteByUsername(username)
                .map(u -> ResponseEntity.ok("Contact deleted successfully"))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }





}







    //a get method for getting all the events from the db
//    @CrossOrigin
//    @GetMapping("/getevents")
//    public Flux<Event> getAllEvents(ResponseBodyEmitter emitter) {
//        Flux<Event> eventFlux = eventRepository.findAll();
//        eventFlux.subscribe(object -> {
//            try {
//                emitter.send(object);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        return eventFlux;

//    }


//    @CrossOrigin
//    @GetMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
//            MongoDatabase database = mongoClient.getDatabase("test");
//            MongoCollection<Document> collection = database.getCollection("users");
//            FindIterable<Document> iterable = collection.find();
//            List<Document> documents = new ArrayList<>();
//            iterable.into(documents);
//            for (Document document : documents) {
//                while (document.get("username").equals(username) && document.get("password").equals(password)) {
//                Token token = new Token();
//                String tokenString = token.generateToken();
//                return tokenString;
//                }
//            }
//            return "Invalid username or password";
//        }
//    }
//
//    @CrossOrigin
//    @GetMapping("/logout")
//    public String logout(@RequestParam String token) {
//        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
//            MongoDatabase database = mongoClient.getDatabase("test");
//            MongoCollection<Document> collection = database.getCollection("users");
//            FindIterable<Document> iterable = collection.find();
//            List<Document> documents = new ArrayList<>();
//            iterable.into(documents);
//            for (Document document : documents) {
//                while (document.get("token").equals(token)) {
//                Token tokens = new Token();
//                String tokenString = tokens.generateToken();
//                return tokenString;
//                }
//            }
//            return "Invalid token";
//        }
//    }
//




