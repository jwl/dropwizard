package com.heartbeater.app.senders;

import java.net.HttpURLConnection;

public interface ISender {
    public HttpURLConnection get(String url, String userAgent) throws HttpSenderException;
    public void post();
}