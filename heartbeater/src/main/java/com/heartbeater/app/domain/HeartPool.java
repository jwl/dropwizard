package com.heartbeater.app.domain;

public class HeartPool {
    private int amountOfHearts;

    public HeartPool(int amountOfHearts) {
        this.amountOfHearts = amountOfHearts;
    }

    public void initiateBeatPool() {
        for (int i = 0; i < this.amountOfHearts; i++) {
            ThreadRunnableHeart parallelHeartBeat = new ThreadRunnableHeart("Heart beat number: " + i);
            parallelHeartBeat.start();
        }
    }
}