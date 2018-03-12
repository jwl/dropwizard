package com.tutorial.app.domain;

import java.util.List;

public class BeatWrapper {
    private List<HeartBeat> beats;

    public List<HeartBeat> getBeats() {
        return this.beats;
    }

    public void setBeats(List<HeartBeat> beats) {
        this.beats = beats;
    }
}