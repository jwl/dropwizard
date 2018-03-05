package com.heartbeater.app.senders;

public class UserHttpSender extends AbstractHttpSender implements IUserSender {
    protected BodyParser parser;
    protected final String USER_AGENT = "Mozilla/5.0";
    // TODO: Get this from a property
    private String url = "http://192.168.99.100:8000/users";

    public UserHttpSender() {
        parser = new BodyParser();
    }

    public String getUsers() {
        try {
            return this.parseBody(get(this.url, this.USER_AGENT), this.parser);
        } catch(HttpSenderException exception) {
            System.out.println(exception.getMessage());
            return null;
        } 
    }
}