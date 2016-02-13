package com.seniordesigndbgt.dashboard.model;

import java.util.ArrayList;

public class User {
    private String username;
    private String defaultView;

    public User(String username, String defaultView) {
        this.username = username;
        this.defaultView = defaultView;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }
}
