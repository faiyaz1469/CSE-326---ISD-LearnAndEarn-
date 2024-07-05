package com.nabil.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Traveller {
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
    private String email;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String subject;
    

    @Getter
    @Setter
    private Double price;

    @Getter
    @Setter
    private String method;
    

    public Traveller() {
    }

    public Traveller(String firstName, String email, String name, String subject, Double price, String method) {
        this.firstName = firstName;
        this.email = email;
        this.name = name;
        this.subject = subject;
        this.price = price;
        this.method = method;
    }


    @Override
    public String toString() {
        return String.format("Traveller(id=%s, firstName=%s, email=%s, name=%s, subject=%s, price=%s, method=%s)", id, firstName, email, name, subject, price, method);
    }
    
}
