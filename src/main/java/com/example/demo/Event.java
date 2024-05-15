package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Document(collection = "Events")
    public class Event {
    private String category;
    @Id
        private int id;
        private String name;
        private String date;
        private String location;
        private String[] attendees;
        private String description;

        public Event(int id, String name, Date date, String location, String[] attendees, String description) {
            this.id = id;
            this.name = name;
            this.date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
            this.location = location;
            this.attendees = attendees;
            this.description = description;
        }

        public Event(String name, String date, String location, String description) {
            this.name = name;
            this.date = date;
            this.location = location;
            this.description = description;
        }

    @JsonCreator
    public Event(@JsonProperty("id") int id,
                 @JsonProperty("name") String name,
                 @JsonProperty("date") String date,
                 @JsonProperty("location") String location,
                 @JsonProperty("attendees") String[] attendees,
                 @JsonProperty("description") String description,
                 @JsonProperty("category") String category) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.attendees = attendees;
        this.description = description;
        this.category = category;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees.toArray(new String[0]);


    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}