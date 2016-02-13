package com.seniordesigndbgt.dashboard.controller;

import com.seniordesigndbgt.dashboard.model.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;

@Controller
public class ApiController {

    @RequestMapping("/stocks")
    public @ResponseBody
    Stock stock(@RequestParam(required=false, defaultValue="World") String name) {
        System.out.println("==== getting stock data ====");
        Stock newStock = new Stock("DB", LocalTime.now(), 33.45);
        return newStock;
    }

}
