package com.tutorial.app.resources;

import com.tutorial.app.models.User;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final AtomicLong counter;

    public UserResource() {
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public User sayHello(@QueryParam("name") Optional<String> name) {
        return new User(counter.incrementAndGet(), "Something");
    }
}