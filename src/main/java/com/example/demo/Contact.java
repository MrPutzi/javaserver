package com.example.demo;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Contacts")
public class Contact {


    public String name;
    public String contact;

    public Contact(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public static Contact createContact(String name, String contact) {
        return new Contact(name, contact);
    }

    public static class ContactId {
        public String name;
        public String contact;
        public String $oid;

        public ContactId() {
            this.name = name;
            this.contact = contact;
        }


        @Override
        public String toString() {
            return "Contact{" +
                    "name='" + name + '\'' +
                    ", contact='" + contact + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public String getContact() {
            return contact;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }


    }
}