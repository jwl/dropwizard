package com.tutorial.app.dao;

import com.tutorial.app.domain.HeartBeat;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class HeartBeatDAO extends AbstractDAO<HeartBeat>  {
    public HeartBeatDAO(SessionFactory factory) {
        super(factory);
    }

    public Long create(HeartBeat beat) {
        return (Long) persist(beat).getId();
    }

    public List<HeartBeat> getUsersBeats(Long id) {
        Query query= currentSession().createQuery("from heartbeat where patientId = :id");
        query.setParameter("id", id);
        return (List<HeartBeat>) query.list();
    }
}