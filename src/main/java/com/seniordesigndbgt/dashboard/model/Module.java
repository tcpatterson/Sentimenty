package com.seniordesigndbgt.dashboard.model;

public class Module {
    private String title;
    private String apiEndpoint;

    public Module(String title, String apiEndpoint) {
        this.title = title;
        this.apiEndpoint = apiEndpoint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public void setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }
}
