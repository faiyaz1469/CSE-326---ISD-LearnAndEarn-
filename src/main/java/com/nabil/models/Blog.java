package com.nabil.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String revname;

    @Getter
    @Setter
    private String title;
    
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String subject;
    

    public Blog() {
    }

    public Blog(String revname, String title, String description, String name, String subject) {
        this.revname = revname;
        this.title = title;
        this.description = description;
        this.name = name;
        this.subject = subject;
    }


    @Override
    public String toString() {
        return String.format("Blog(id=%s, revname=%s, title=%s, description=%s, name=%s, subject=%s)", id, revname, title, description, name, subject);
    }
    
}
