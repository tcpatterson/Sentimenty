package com.seniordesigndbgt.dashboardtest.modeltests;

import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;
import com.seniordesigndbgt.dashboard.model.DailyStock;

import java.time.*;

import java.util.List;
/**
 * Created by kamehardy on 3/27/16.
 */
public class DailyStockTest {


    @Test
    public void testGetSymbol() throws Exception {
        String str = "XYN";
        DailyStock stock = new DailyStock();
        stock.setSymbol(str);
        assertEquals(str, stock.getSymbol());
    }

    @Test
    public void testGetTime() throws Exception {
        DailyStock stock = new DailyStock();
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        stock.setTime(today);
        assertEquals(today, stock.getTime());

    }

    @Test
    public void testGetValue() throws Exception {
        DailyStock stock = new DailyStock();
        double val = 18.40;
        stock.setValue(val);
        assertEquals(val, stock.getValue(), 0.0);
    }

}