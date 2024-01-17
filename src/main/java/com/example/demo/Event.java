package com.example.demo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Document(collection = "Events")
public class Event {


    public String getFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(this.date);
    }

    public Event(Object id, Object name, Object date, Object location, Object attendees, Object description) {
    }

    public static class EventId {
        public String $oid;
    }


    @Id
    public EventId _id;
    public int id;
    public String name;
    public Date date; // Change this to Date type
    public String location;
    public List<String> attendees;
    public String description;

    public Event(EventId _id, int id, String name, Date date, String location, List<String> attendees, String description) {
        this._id = _id;
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.attendees = attendees;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", attendees=" + attendees +
                ", description='" + description + '\'' +
                '}';
    }

    public EventId get_id() {
        return _id;
    }

    public void set_id(EventId _id) {
        this._id = _id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
