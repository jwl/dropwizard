package com.heartbeater.app;

import java.lang.management.ManagementFactory;
import java.util.List;

import com.heartbeater.app.domain.HeartPool;
import com.heartbeater.app.domain.Patient;
import com.heartbeater.app.senders.IUserSender;
import com.heartbeater.app.senders.UserHttpSender;


public class App 
{

    // It would be nice to have a monitor that instances this same application N times
    public static void main( String[] args )
    {
        // System.out.print(getProcessId("<PID>"));
        IUserSender sender = new UserHttpSender();
        List<Patient> result = sender.getPatients();
        
        try {
            HeartPool heartPool = new HeartPool(result);
            heartPool.initiateBeatPool();
        } catch(InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    private static String getProcessId(final String fallback) {
        // Note: may fail in some JVM implementations
        // therefore fallback has to be provided
    
        // something like '<pid>@<hostname>', at least in SUN / Oracle JVMs
        final String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        final int index = jvmName.indexOf('@');
    
        if (index < 1) {
            // part before '@' empty (index = 0) / '@' not found (index = -1)
            return fallback;
        }
    
        try {
            return Long.toString(Long.parseLong(jvmName.substring(0, index)));
        } catch (NumberFormatException e) {
            // ignore
        }
        return fallback;
    }
}
