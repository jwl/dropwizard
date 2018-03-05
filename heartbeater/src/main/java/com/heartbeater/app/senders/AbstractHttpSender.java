package com.heartbeater.app.senders;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;

public abstract class AbstractHttpSender implements ISender {

    public HttpURLConnection get(String url, String userAgent) throws HttpSenderException {
        try {
            URL destination = new URL(url);
            
            return connect(destination, userAgent);
        } catch (MalformedURLException malformedURL) {
            throw new HttpSenderException(String.format("The URL given is malformed (%s).", url));
        }
    }

    public String parseBody(HttpURLConnection connection, BodyParser parser) throws HttpSenderException {
        try {
            return parser.parseIncomingBody(connection);
        } catch (IOException exception) {
            throw new HttpSenderException("There was a parsing problem when parsing the request.");
        }
    }

    private HttpURLConnection connect(URL url, String userAgent) throws HttpSenderException {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", userAgent);
            int responseCode = con.getResponseCode();
            if (responseCode >= 200 && responseCode <= 300) {
                return con;
            } else {
                throw new HttpSenderException("Response code wasn't in 200's range.");
            }
        } catch (IOException ioException) {
            throw new HttpSenderException("There was a problem with the connection.");
        }
    }

    public void post() {

    }
}