package com.tutorial.app.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "heartbeats")
public class HeartBeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date dateBeatOccurred;

    @OneToOne
    @JoinColumn(nullable = false, name = "patientId")
    private User patient;

    public HeartBeat() {
        // Jackson deserialization
    }

    public HeartBeat(Long id, Date dateBeatOccurred, User patient) {
        this.id = id;
        this.dateBeatOccurred = dateBeatOccurred;
        this.patient = patient;
    }

    @JsonProperty
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateBeatOccured() {
        return this.dateBeatOccurred;
    }

    public void setDateBeatOccured(Date dateBeatOccurred) {
        this.dateBeatOccurred = dateBeatOccurred;
    }

    public long getPatientId() {
        return this.patient.getId();
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }
}