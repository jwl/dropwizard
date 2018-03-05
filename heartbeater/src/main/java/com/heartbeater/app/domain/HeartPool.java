package com.heartbeater.app.domain;

import java.util.List;

public class HeartPool {
    private List<Patient> patientList;

    public HeartPool(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public void initiateBeatPool() throws InterruptedException {
        for (int i = 0; i < this.patientList.size(); i++) {
            ThreadRunnableHeart parallelHeartBeat = new ThreadRunnableHeart("Heart-" + i, this.patientList.get(i));
            Thread.sleep(2000);
            parallelHeartBeat.start();
        }
    }
}