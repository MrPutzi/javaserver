package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll().collectList().block();
    }

    public Event getEventById(String id) {
        return eventRepository.findById(id).block();
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event).block();
    }

    public Event updateEvent(String id, Event event) {
        Event existingEvent = eventRepository.findById(id).block();
        if (existingEvent != null) {
            existingEvent.setEventId(event.getEventId());
            existingEvent.setName(event.getName());
            existingEvent.setDate(event.getDate());
            existingEvent.setLocation(event.getLocation());
            existingEvent.setAttendees((ArrayList) event.getAttendees());
            existingEvent.setDescription(event.getDescription());
            return eventRepository.save(existingEvent).block();
        }
        return null;
    }

    public void deleteEvent(String id) {
        Event existingEvent = eventRepository.findById(id).block();
        if (existingEvent != null) {
            eventRepository.delete(existingEvent).block();
        }
    }

    public Event attendEvent(String eventId, String username) {
        Event event = eventRepository.findById(eventId).blockOptional().orElseThrow(() -> new RuntimeException("Event not found"));
        List<String> attendees = event.getAttendees();
        attendees.add(username);
        event.setAttendees((ArrayList) attendees);
        return eventRepository.save(event).block();
    }
}