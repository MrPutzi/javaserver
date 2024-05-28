package com.example.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/api/events")
@RestController
public class Controller {
    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        Event event = eventService.getEventById(id);
        return event != null ? new ResponseEntity<>(event, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return updatedEvent != null ? new ResponseEntity<>(updatedEvent, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    private  MongoCollection<Document> eventCollection;
    private  ReactiveMongoTemplate reactiveMongoTemplate;
    private  TokenManagement tokenManagement;
//
//    public Controller(MongoClient mongoClient, ReactiveMongoTemplate reactiveMongoTemplate, TokenManagement tokenManagement) {
//        MongoDatabase database = mongoClient.getDatabase("MeetHub");
//        this.eventCollection = database.getCollection("Events");
//        this.reactiveMongoTemplate = reactiveMongoTemplate;
//        this.tokenManagement = tokenManagement;
//    }
//



    @PostMapping("/{eventId}/attend")
    public ResponseEntity<Event> attendEvent(@PathVariable String eventId, @RequestBody Map<String, String> body) {
        String username = body.get("username");
        Event event = eventService.attendEvent(eventId, username);
        return ResponseEntity.ok(event);
    }
//
//    /*    EVENT HANDLING      */
//    @CrossOrigin
//    @GetMapping("/getevents")
//    public Mono<List<Event>> getAllEvents() {
//        return reactiveMongoTemplate.findAll(Document.class, "Events")
//                .map(this::convertDocumentToEvent)
//                .collectList();
//    }
//
//
//    private Event convertDocumentToEvent(Document document) {
//        String dateString = document.getString("date");
//        Date date = null;
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(dateString);
//        } catch (ParseException e) {
//            // Handle the exception appropriately, e.g., log the error or use a default date
//            e.printStackTrace();
//        }
//        return new Event(
//                document.getInteger("id", 0),
//                document.getString("name"),
//                date,
//                document.getString("location"),
//                new ArrayList<>(document.getList("attendees", String.class)),
//                document.getString("description")
//        );
//    }
//
//    @CrossOrigin
//    @GetMapping("/geteventbyname/{name}")
//    public Mono<Event> getEventByName(@PathVariable String name) {
//        return eventRepository.findByName(name);
//    }
//
//
//
//    @CrossOrigin
//    @DeleteMapping("/deleteEvent/{id}")
//    public Mono<ResponseEntity<Void>> deleteEvent(@PathVariable int id) {
//        return eventRepository.deleteById(id)
//                .map(r -> ResponseEntity.ok().<Void>build())
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }
//
//    @CrossOrigin
//    @PostMapping("/addEvent")
//    public Mono<Event> createEvent(@RequestBody Event event) {
//        return eventRepository.save(event);
//    }
//
//    @CrossOrigin
//    @GetMapping("/geteventbyid/{id}")
//    public Mono<Event> getEventById(@PathVariable String id) {
//        return eventRepository.findById(id);
//    }
//
//
//}

}