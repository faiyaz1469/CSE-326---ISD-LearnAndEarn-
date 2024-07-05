package com.nabil.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="account",uniqueConstraints=@UniqueConstraint(columnNames={"id","email"}))
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; 
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String chips; 
    //private String fullName;

    @ManyToOne
    private Role role; 

    // @ManyToMany(fetch = FetchType.EAGER)
    // @JoinTable(
    //     name = "account_role", 
    //     joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
    //     inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")}
    // )
    // private Set<Role> roles = new HashSet<>();

    public Account(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}