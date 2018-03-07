package com.heartbeater.app.domain;

import com.heartbeater.app.senders.IUserSender;
import com.heartbeater.app.senders.UserHttpSender;

// This in fact should be an abstract class that recieves an entity, and calls a run|start|init method
// which they should implement. As for now, we will leave it this way.
public class ThreadRunnableHeart implements Runnable {
    private Thread thread;
    private String threadName;
    private Heart heart;
    private IUserSender sender;

    public ThreadRunnableHeart(String threadName, Patient patient) {
        this.threadName = threadName;
        this.sender = new UserHttpSender();
        this.heart = new Heart(patient);        
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
            if (heart.getBeats().size() % 100 == 0) {
                // Save ?
                sender.sendBeats(heart);
                heart.printBeats();
                heart.getBeats().clear();
            }
            heart.beat();
        }
    }
}