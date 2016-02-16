package com.seniordesigndbgt.dashboard.model;


import java.util.ArrayList;

public class StockHistory {
    private ArrayList<DailyStock> today;
    private ArrayList<DailyStock> month;
    private ArrayList<DailyStock> oneYear;
    private ArrayList<DailyStock> fiveYears;

    public StockHistory(ArrayList<DailyStock> today, ArrayList<DailyStock> month, ArrayList<DailyStock> oneYear, ArrayList<DailyStock> fiveYears) {
        this.today = today;
        this.month = month;
        this.oneYear = oneYear;
        this.fiveYears = fiveYears;
    }

    public ArrayList<DailyStock> getToday() {
        return today;
    }

    public void setToday(ArrayList<DailyStock> today) {
        this.today = today;
    }

    public ArrayList<DailyStock> getMonth() {
        return month;
    }

    public void setMonth(ArrayList<DailyStock> month) {
        this.month = month;
    }

    public ArrayList<DailyStock> getOneYear() {
        return oneYear;
    }

    public void setOneYear(ArrayList<DailyStock> oneYear) {
        this.oneYear = oneYear;
    }

    public ArrayList<DailyStock> getFiveYears() {
        return fiveYears;
    }

    public void setFiveYears(ArrayList<DailyStock> fiveYears) {
        this.fiveYears = fiveYears;
    }
}
