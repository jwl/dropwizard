package com.heartbeater.app.senders;

import com.heartbeater.app.domain.Heart;
import com.heartbeater.app.domain.Patient;
import java.util.List;

public interface IUserSender {
    public List<Patient> getPatients();
    public void sendBeats(Heart beats);
}