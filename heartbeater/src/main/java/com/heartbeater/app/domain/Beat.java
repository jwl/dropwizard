package com.heartbeater.app.domain;

public class Beat {
    private String dateBeatOccurred;
    private Patient patient;
    public Beat() {

    }

    public Beat(String date, Patient patient) {
        this.dateBeatOccurred = date;
        this.patient = patient;
    }

    public String getDateBeatOccurred() {
        return this.dateBeatOccurred;
    }

    public void setDateBeatOccurred(String date) {
        this.dateBeatOccurred = date;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}