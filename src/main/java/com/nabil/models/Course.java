package com.nabil.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String firstName;
    
    @Getter
    @Setter
    private String email;
    
    @Getter
    @Setter
    private String level;

    @Getter
    @Setter
    private double price;
    
    @Getter
    @Setter
    private String image;

    @Getter
    @Setter
    private String description;
    

    public Course() {
    }

    public Course(String name, String subject, String firstName, String email, String level, Double price, String image, String description) {
        this.name = name;
        this.subject = subject;
        this.firstName = firstName;
        this.email = email;
        this.level = level;
        this.price = price;
        this.image = image;
        this.description = description;
    }


    @Override
    public String toString() {
        return String.format("Course(id=%s, name=%s, subject=%s, firstName=%s, email=%s, level=%s, price=%s, image=%s, description=%s)", id, name, firstName, email, subject, level, price, image, description);
    }
    
}
