package com.tutorial.app;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Beat {
    private ArrayList<String> beatRecords;
    private DateFormat dateFormat;
    private Random random;

    public Beat() {
        random = new Random();
        beatRecords = new ArrayList<String>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public ArrayList<String> getRecords() {
        return this.beatRecords;
    }

    public void startBeating() {
        while (true) {
            try {
                int wait = (60 + random.nextInt((100 - 60) + 1)) * 10;
                TimeUnit.MILLISECONDS.sleep(wait);
                beatRecords.add(dateFormat.format(new Date()));
            } catch (InterruptedException exception) {
                System.out.print(exception);
            }
        }
    }
}