package com.seniordesigndbgt.dashboard.model;


import java.util.ArrayList;

public class StockHistory {
    private ArrayList<Stock> today;
    private ArrayList<Stock> month;
    private ArrayList<Stock> oneYear;
    private ArrayList<Stock> fiveYears;

    public StockHistory(ArrayList<Stock> today, ArrayList<Stock> month, ArrayList<Stock> oneYear, ArrayList<Stock> fiveYears) {
        this.today = today;
        this.month = month;
        this.oneYear = oneYear;
        this.fiveYears = fiveYears;
    }

    public ArrayList<Stock> getToday() {
        return today;
    }

    public void setToday(ArrayList<Stock> today) {
        this.today = today;
    }

    public ArrayList<Stock> getMonth() {
        return month;
    }

    public void setMonth(ArrayList<Stock> month) {
        this.month = month;
    }

    public ArrayList<Stock> getOneYear() {
        return oneYear;
    }

    public void setOneYear(ArrayList<Stock> oneYear) {
        this.oneYear = oneYear;
    }

    public ArrayList<Stock> getFiveYears() {
        return fiveYears;
    }

    public void setFiveYears(ArrayList<Stock> fiveYears) {
        this.fiveYears = fiveYears;
    }
}
