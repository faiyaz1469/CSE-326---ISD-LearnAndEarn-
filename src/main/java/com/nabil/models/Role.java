package com.nabil.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @Column(length = 16)
    private String name;

    public Role(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Role(name=%s)", name);
    }
}
