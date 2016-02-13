package com.seniordesigndbgt.dashboard.model;

import java.time.LocalTime;

public class Stock {
    private String symbol;
    private LocalTime Time;
    private double value;

    public Stock(String symbol, LocalTime time, double value) {
        this.symbol = symbol;
        Time = time;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
