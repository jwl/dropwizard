package com.heartbeater.app.domain;

// This in fact should be an abstract class that recieves an entity, and calls a run|start|init method
// which they should implement. As for now, we will leave it this way.
public class ThreadRunnableHeart implements Runnable {
    private Thread thread;
    private String threadName;
    private Heart heart;

    public ThreadRunnableHeart(String threadName) {
        this.threadName = threadName;
        this.heart = new Heart();
        System.out.println("Thread starting: " + threadName);
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    public void run() {
        while (true) {
            if (heart.getBeats().size() % 1000 == 0) {
                // Save on database
            }
            heart.beat();
        }
    }
}