package com.heartbeater.app.senders;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpSender implements ISender{

    private final String USER_AGENT = "Mozilla/5.0";
    // TODO: Get this from a property
    private String url = "http://192.168.99.100:8000/users";

    public HttpSender() {
        responseParser = new ResponseParser();
    }
    private ResponseParser responseParser;

    public String getUsers() {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                return responseParser.parseResponse(con);
            } else {
                return "";
            }
        } catch (MalformedURLException malformedURL) {
            System.out.print("Url passed is not formatted correctly.");
            return null;
        } catch (IOException ioException) {
            System.out.println("There was a problem with connecting to the specified api.");
            return null;
        }
    }
    public void sendHeartRate() {
    }
}