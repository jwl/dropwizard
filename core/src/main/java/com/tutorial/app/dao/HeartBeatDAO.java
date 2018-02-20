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

    public List<HeartBeat> getPatientHeartbeats(Long id) {
        // When using HQL, entity name should be the one that is being target, not table name.
        Query query= currentSession().createQuery("from HeartBeat where patientId = :id");
        query.setParameter("id", id);
        return (List<HeartBeat>) (List) query.list();
    }
}