package com.seniordesigndbgt.dashboard.model;

import java.util.ArrayList;

public class View {
    private String username;
    private String name;
    private ArrayList<Module> columnOne;
    private ArrayList<Module> columnTwo;
    private ArrayList<Module> columnThree;

    public View(String username, String name, ArrayList<Module> columnOne, ArrayList<Module> columnTwo, ArrayList<Module> columnThree) {
        this.username = username;
        this.name = name;
        this.columnOne = columnOne;
        this.columnTwo = columnTwo;
        this.columnThree = columnThree;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Module> getColumnOne() {
        return columnOne;
    }

    public void setColumnOne(ArrayList<Module> columnOne) {
        this.columnOne = columnOne;
    }

    public ArrayList<Module> getColumnTwo() {
        return columnTwo;
    }

    public void setColumnTwo(ArrayList<Module> columnTwo) {
        this.columnTwo = columnTwo;
    }

    public ArrayList<Module> getColumnThree() {
        return columnThree;
    }

    public void setColumnThree(ArrayList<Module> columnThree) {
        this.columnThree = columnThree;
    }
}
