package com.tutorial.app.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    public User() {
        // Jackson deserialization
    }

    public User(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    @JsonProperty
    public Long getId() {
        return this.id;
    }

    @JsonProperty
    public String getContent() {
        return this.comment;
    }
}