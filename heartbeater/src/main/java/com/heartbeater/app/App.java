package com.heartbeater.app;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartbeater.app.domain.HeartPool;
import com.heartbeater.app.domain.Patient;
import com.heartbeater.app.senders.HttpSender;
import com.heartbeater.app.senders.ISender;


public class App 
{

    // It would be nice to have a monitor that instances this same application N times
    public static void main( String[] args ) throws JsonParseException, JsonMappingException
    {
        // System.out.print(getProcessId("<PID>"));
        ISender sender = new HttpSender();
        String result = sender.getUsers();
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Patient> patientsList = objectMapper.readValue(result, new TypeReference<List<Patient>>(){});
            HeartPool heartPool = new HeartPool(patientsList);
            heartPool.initiateBeatPool();
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        } catch(InterruptedException exception) {
            System.out.println(exception.getMessage());
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
