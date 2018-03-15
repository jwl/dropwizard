package com.tutorial.app.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    public Date getDateBeatOccurred() {
        return this.dateBeatOccurred;
    }

    public void setDateBeatOccurred(Date dateBeatOccurred) {
        this.dateBeatOccurred = dateBeatOccurred;
    }

    public User getPatient() {
        return this.patient;
    }
    
    public void setPatient(User patient) {
        this.patient = patient;
    }
}