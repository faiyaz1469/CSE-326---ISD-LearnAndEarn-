package com.nabil.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Quesans {
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
    private String quesdescription;

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
    private String ansdescription;
    

    public Quesans() {
    }

    public Quesans(String revname, String quesdescription, String name, String subject, String firstName, String email, String ansdescription) {
        this.revname = revname;
        this.quesdescription = quesdescription;
        this.name = name;
        this.subject = subject;
        this.firstName = firstName;
        this.email = email;
        this.ansdescription = ansdescription;
    }


    @Override
    public String toString() {
        return String.format("Blog(id=%s, revname=%s, quesdescription=%s, name=%s, subject=%s, firstName=%s, email=%s, ansdescription=%s)", id, revname, quesdescription, name, subject, firstName, email, ansdescription);
    }
    
}
