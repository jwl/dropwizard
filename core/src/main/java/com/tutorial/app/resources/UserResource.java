package com.tutorial.app.resources;

import com.tutorial.app.domain.BeatWrapper;
import com.tutorial.app.domain.HeartBeat;
import com.tutorial.app.domain.User;

import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;

import com.tutorial.app.dao.HeartBeatDAO;
import com.tutorial.app.dao.UserDAO;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// Unit of work annotation used for transactional purposes which:
// - opens a transaction, call create, commit the transaction and close the session.
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDAO userDAO;
    private HeartBeatDAO hbDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserResource(UserDAO userDAO, HeartBeatDAO hbDAO){
        this.userDAO = userDAO;
        this.hbDAO = hbDAO;
    }

    @GET
    @UnitOfWork
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Response getById(@PathParam("id") Long id) {
        User user = userDAO.getById(id);
        if (user != null) {
            return Response.ok(user).build();
        }
        return Response.status(404).build();
    }

    @POST
    @UnitOfWork
    public Long create(User user) {
        return userDAO.create(user);
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public void update(@PathParam("id") Long id, User user) {
        // TODO: Find a clean way to return 404
        user.setId(id);
        userDAO.update(user);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response remove(@PathParam("id") Long id) {
        User user = userDAO.getById(id);
        if (user == null) {
            return Response.status(404).build();
        }
        userDAO.remove(user);
        return Response.status(200).build();
    }

    @GET
    @Path("/{id}/heartbeats")
    @UnitOfWork
    public Response getPatientHeartbeats(@PathParam("id") Long id) {
        User user = userDAO.getById(id);
        if (user == null) {
            return Response.status(404).build();
        }
        List<HeartBeat> patientHeartBeats = hbDAO.getPatientHeartbeats(id);
        return Response.ok(patientHeartBeats).build();
    }
}