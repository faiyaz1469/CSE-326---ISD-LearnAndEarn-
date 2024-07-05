package com.nabil.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstName;
    
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
    private String email;
    
    @Getter
    @Setter
    private String link;

    @Getter
    @Setter
    private String mark;
    

    public Submission() {
    }

    public Submission(String firstName, String title, String description, String subject, String name, String email, String link, String mark) {
        this.firstName = firstName;
        this.title = title;
        this.description = description;
        this.subject = subject;
        this.name= name;
        this.email = email;
        this.link = link;
        this.mark = mark;
    }


    @Override
    public String toString() {
        return String.format("Submission(id=%s, firstName=%s, title=%s, description=%s, subject=%s, name=%s, email=%s, link=%s, mark=%s)", id, firstName, title, description, subject, name, email, link, mark);
    }
    
}
