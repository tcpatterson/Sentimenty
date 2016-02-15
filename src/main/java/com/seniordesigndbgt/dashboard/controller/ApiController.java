package com.seniordesigndbgt.dashboard.controller;

import com.seniordesigndbgt.dashboard.model.DailyStock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;

@Controller
public class ApiController {

    @RequestMapping("/stocks")
    public @ResponseBody
    DailyStock stock() {
        System.out.println("==== getting stock data ====");
        DailyStock newStock = new DailyStock("DB", LocalTime.now(), 33.45);
        return newStock;
    }

}
