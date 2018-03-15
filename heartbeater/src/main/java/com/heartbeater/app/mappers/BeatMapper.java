package com.heartbeater.app.mappers;

import com.heartbeater.app.domain.Beat;
import com.heartbeater.app.domain.Heart;

import java.util.ArrayList;

public class BeatMapper {
    private ArrayList<Beat> beats;

    public BeatMapper() { }

    public BeatMapper(Heart heart) {
        beats = new ArrayList<Beat>();
        for (String dateOccurred : heart.getBeats()) {
            beats.add(new Beat(dateOccurred, heart.getPatient()));
        }
    }

    public ArrayList<Beat> getBeats() {
        return this.beats;
    }

    public void setBeats(ArrayList<Beat> beats) {
        this.beats = beats;
    }
}
