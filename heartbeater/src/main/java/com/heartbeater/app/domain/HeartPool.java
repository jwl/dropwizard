package com.heartbeater.app.domain;

import java.util.Random;

public class HeartPool {
    private int amountOfHearts;

    public HeartPool(int amountOfHearts) {
        this.amountOfHearts = amountOfHearts;
    }

    public void initiateBeatPool() throws InterruptedException {
        for (int i = 0; i < this.amountOfHearts; i++) {
            ThreadRunnableHeart parallelHeartBeat = new ThreadRunnableHeart("Heart beat number: " + i);
            Random rdn = new Random();
            Thread.sleep(2000);
            parallelHeartBeat.start();
        }
    }
}