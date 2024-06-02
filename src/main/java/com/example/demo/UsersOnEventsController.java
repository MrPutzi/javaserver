package com.example.demo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users-on-events")
@CrossOrigin
public class UsersOnEventsController {

    @Autowired
    private UsersOnEventsRepository usersOnEventsRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventService eventService;


   //add an users id to a event id
    @PostMapping("/add/{eventId}/{userId}")
    public ResponseEntity<UsersOnEvents> addUsersOnEvents(@PathVariable String eventId, @PathVariable String userId) {
        Event event = eventService.getEventById(eventId);
        User user = userRepository.findById(userId).block();
        if (event != null && user != null) {
            UsersOnEvents usersOnEvents = new UsersOnEvents(new ObjectId(userId), new ObjectId(eventId));
            return new ResponseEntity<>(usersOnEventsRepository.save(usersOnEvents).block(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/is-user-attending/{eventId}/{userId}")
    public boolean isUserAttending(@PathVariable String eventId, @PathVariable String userId) {
        return usersOnEventsRepository.existsByEventIdAndUserId(eventId, userId);
    }


    //delete a user from a event
    @DeleteMapping("/delete/{eventId}/{userId}")
    public ResponseEntity<Void> deleteUsersOnEvents(@PathVariable String eventId, @PathVariable String userId) {
        Event event = eventService.getEventById(eventId);
        User user = userRepository.findById(userId).block();
        if (event != null && user != null) {
            try {
                usersOnEventsRepository.deleteByUserIdAndEventId(new ObjectId(userId), new ObjectId(eventId)).block();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}