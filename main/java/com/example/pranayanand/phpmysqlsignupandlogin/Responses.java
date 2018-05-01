package com.example.pranayanand.phpmysqlsignupandlogin;

import java.util.List;

/**
 * Created by Pranay Anand on 25-12-2017.
 */

public class Responses {

    private boolean error;
    private String message;
    private List<User> user;

    public Responses(boolean error, String message, List<User> user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<User> getUser() {
        return user;
    }
}
