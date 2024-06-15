package com.example.demo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RequestMapping("/api/events")
@RestController
@CrossOrigin
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

    @PostMapping("/add")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Event>> updateEvent(@PathVariable String id, @RequestBody Event updatedEvent) {
        return eventRepository.findById(id)
                .flatMap(existingEvent -> {
                    existingEvent.setName(updatedEvent.getName());
                    existingEvent.setDate(updatedEvent.getDate());
                    existingEvent.setLocation(updatedEvent.getLocation());
                    existingEvent.setDescription(updatedEvent.getDescription());
                    return eventRepository.save(existingEvent);
                })
                .map(savedEvent -> new ResponseEntity<>(savedEvent, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    private MongoCollection<Document> eventCollection;
    private ReactiveMongoTemplate reactiveMongoTemplate;
    private TokenManagement tokenManagement;

    public Controller(UserRepository userRepository, EventRepository eventRepository, ReactiveMongoTemplate reactiveMongoTemplate, TokenManagement tokenManagement) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.tokenManagement = tokenManagement;
    }
}
