package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "Events")
public class Event {
//    @Id
//    private int id;
//    private String name;
//    private Date date;
//    private String location;
//    private String description;
//    private ArrayList attendees;
@Id
private String id;
    private int eventId;
    private String name;
    private Instant date;
    private String location;
    private String description;

    public Event(int eventId, String name, Date date, String location, String description, List<String> attendees) {
        this.eventId = eventId;
        this.name = name;
        this.date = date.toInstant();
        this.location = location;
        this.description = description;
    }

    @JsonCreator
    public Event(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("date") String date, @JsonProperty("location") String location, @JsonProperty("description") String description) {
        this.id = id;
        this.name = name;
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date).toInstant();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.location = location;
        this.description = description;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;

    }


    public Event(int id, String name, Date date, String location, String description) {
        this.id = String.valueOf(id);
        this.name = name;
        this.date = date.toInstant();
        this.location = location;
        this.description = description;
    }

    public Event() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}