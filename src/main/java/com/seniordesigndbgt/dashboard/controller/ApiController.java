package com.seniordesigndbgt.dashboard.controller;

import com.seniordesigndbgt.dashboard.dao.DailyStockDAO;
import com.seniordesigndbgt.dashboard.dao.StockHistoryDAO;
import com.seniordesigndbgt.dashboard.model.DailyStock;
import com.seniordesigndbgt.dashboard.model.StockHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Table;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApiController {

    @Autowired
    private DailyStockDAO _dailyStockDao;
    @Autowired
    private StockHistoryDAO _stockHistoryDao;

    @RequestMapping("/stocks")
    public @ResponseBody
    List stock() {
        List<DailyStock> todayStocks = _dailyStockDao.getAll();
        List<StockHistory> oldStocks = _stockHistoryDao.getAll();
        List<List> allStocks = new ArrayList<>();
        allStocks.add(todayStocks);
        allStocks.add(oldStocks);

//        System.out.println("==== getting stock data ====");
//        DailyStock newStock = new DailyStock("DB", LocalTime.now(), 33.45);
        return allStocks;
    }

}
