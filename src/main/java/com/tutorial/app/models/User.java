package com.tutorial.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class User {
    private long id;

    @Length(max = 10)
    private String comment;

    public User() {
        // Jackson deserialization
    }

    public User(long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    @JsonProperty
    public long getId() {
        return this.id;
    }

    @JsonProperty
    public String getContent() {
        return this.comment;
    }
}