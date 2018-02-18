package com.tutorial.app.resources;

import com.tutorial.app.domain.User;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import com.tutorial.app.dao.UserDAO;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<User> getAll() {
        return userDAO.findAll();
    }

    @POST
    @Timed
    @UnitOfWork
    public User find(@PathParam("id") Long id) {
        return userDAO.findById(id);
    }
}