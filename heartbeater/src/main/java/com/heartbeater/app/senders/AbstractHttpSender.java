package com.heartbeater.app.senders;

import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.DataOutputStream;
import java.io.IOException;
import com.heartbeater.app.domain.Heart;
import com.heartbeater.app.exceptions.HttpSenderException;

public abstract class AbstractHttpSender {
    
    public HttpURLConnection get(String url, String userAgent) throws HttpSenderException {
        try {
            HttpURLConnection con = connect(url, userAgent, "GET");
            int responseCode = con.getResponseCode();
            if (responseCode >= 200 && responseCode <= 300) {
                return con;
            } else {
                throw new HttpSenderException("Response code wasn't in 200's range.");
            }
        } catch (IOException exception) {
            throw new HttpSenderException("There was a problem with the connection.");
        }
    }

    public HttpURLConnection post(String url, String userAgent, String data) throws HttpSenderException {
        HttpURLConnection con = connect(url, userAgent, "POST");
        con.setDoOutput(true);
        try {
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        
            wr.writeBytes(data);
            wr.flush();
            wr.close();
            return con;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    private HttpURLConnection connect(String url, String userAgent, String method) throws HttpSenderException {
        try {
            URL destination = new URL(url);
            HttpURLConnection con = (HttpURLConnection) destination.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("User-Agent", userAgent);
            return con;
        } catch (MalformedURLException malformedURL) {
            throw new HttpSenderException(String.format("The URL given is malformed (%s).", url));

        } catch (IOException ioException) {
            throw new HttpSenderException("There was a problem with the connection.");
        }
    }
}