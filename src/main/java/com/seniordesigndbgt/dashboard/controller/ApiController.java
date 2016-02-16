package com.seniordesigndbgt.dashboard.controller;

import com.seniordesigndbgt.dashboard.dao.DailyStockDAO;
import com.seniordesigndbgt.dashboard.model.DailyStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;
import java.util.List;

@Controller
public class ApiController {

    @Autowired
    private DailyStockDAO _dailyStockDao;

    @RequestMapping("/stocks")
    public @ResponseBody
    List stock() {
        List<DailyStock> stocks = _dailyStockDao.getAll();
//        System.out.println("==== getting stock data ====");
//        DailyStock newStock = new DailyStock("DB", LocalTime.now(), 33.45);
        return stocks;
    }

}
