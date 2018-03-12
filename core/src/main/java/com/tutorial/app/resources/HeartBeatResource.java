package com.tutorial.app.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import com.tutorial.app.dao.HeartBeatDAO;
import com.tutorial.app.domain.BeatWrapper;
import com.tutorial.app.domain.HeartBeat;;

@Path("/beats")
@Produces(MediaType.APPLICATION_JSON)
public class HeartBeatResource {
    private HeartBeatDAO hbDAO;

    public HeartBeatResource(HeartBeatDAO hbDAO) {
        this.hbDAO = hbDAO;
    }

    private boolean beatsAreValid(List<HeartBeat> beats) {
        for (HeartBeat beat : beats) {
            if ((Long)beat.getPatient().getId() == null) {
                return false;
            }
        }
        return true;
    }

    @POST
    @Path("/bulk")
    @UnitOfWork
    public Response createBeatBatch(BeatWrapper wrapper) {
        if (wrapper.getBeats().size() == 0 || !beatsAreValid(wrapper.getBeats())) {
            return Response.status(400).build();
        }
        hbDAO.bulkCreate(wrapper.getBeats());
        return Response.ok(wrapper.getBeats().size()).build();
    }
}