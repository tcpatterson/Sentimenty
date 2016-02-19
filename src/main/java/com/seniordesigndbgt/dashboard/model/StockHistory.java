package com.seniordesigndbgt.dashboard.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
@Entity
@Table(name="stockhistory")
public class StockHistory {
    @Id
    @NotNull
    @Column(name="dateStock")
    private String dateStock;
    @NotNull
    @Column(name="closePrice")
    private double closePrice;

    public StockHistory(String dateStock, double closePrice) {
        this.dateStock = dateStock;
        this.closePrice = closePrice;
    }

    public String getDateStock() {
        return dateStock;
    }

    public void setDateStock(String dateStock) {
        this.dateStock = dateStock;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }
}
