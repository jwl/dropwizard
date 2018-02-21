package com.heartbeater.app.domain;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Heart {
    private ArrayList<String> beatRecords;
    private DateFormat dateFormat;
    private Random random;

    public Heart() {
        random = new Random();
        beatRecords = new ArrayList<String>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public void beat() {
        try {
            int wait = (60 + random.nextInt((100 - 60) + 1)) * 10;
            TimeUnit.MILLISECONDS.sleep(wait);
            beatRecords.add(dateFormat.format(new Date()));
        } catch (InterruptedException exception) {
            System.out.print(exception);
        }
    }

    public ArrayList<String> getBeats() {
        return this.beatRecords;
    }

    public void printBeats() {
        for (int index = 0; index < this.beatRecords.size(); index++) {
            String current = this.beatRecords.get(index);
            System.out.println(current);
        }
    }
}
