package com.nabil.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String title;
    
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private String link;
    

    public Lecture() {
    }

    public Lecture(String title, String description, String subject, String name, String link) {
        this.title = title;
        this.description = description;
        this.subject = subject;
        this.name= name;
        this.link = link;
    }


    @Override
    public String toString() {
        return String.format("Lecture(id=%s, title=%s, description=%s, subject=%s, name=%s, link=%s)", id, title, description, subject, name, link);
    }
    
}
