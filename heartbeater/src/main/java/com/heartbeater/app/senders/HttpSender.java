package com.heartbeater.app.senders;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpSender implements ISender{

    private final String USER_AGENT = "Mozilla/5.0";
    private String url = "http://localhost:8080/users";

    public String getUsers() {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println(responseCode);
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                System.out.println(response.toString());
                return response.toString();
            } else {
                return "";
            }
        } catch (MalformedURLException malformedURL) {
            System.out.print("Exception");
            return null;
        } catch (IOException ioException) {
            System.out.println("Something occured");
            return null;
        }

    }
    public void sendHeartRate() {
    }
}