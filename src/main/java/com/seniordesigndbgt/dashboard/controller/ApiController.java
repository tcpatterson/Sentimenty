package com.seniordesigndbgt.dashboard.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seniordesigndbgt.dashboard.dao.DailyStockDAO;
import com.seniordesigndbgt.dashboard.dao.PressDAO;
import com.seniordesigndbgt.dashboard.dao.StockHistoryDAO;
import com.seniordesigndbgt.dashboard.model.DailyStock;
import com.seniordesigndbgt.dashboard.model.Press;
import com.seniordesigndbgt.dashboard.model.StockHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private PressDAO _pressDAO;

    @RequestMapping("/stocks")
    public @ResponseBody
    List stock() {
        List<DailyStock> todayStocks = _dailyStockDao.getAll();
        List<StockHistory> oldStocks = _stockHistoryDao.getAll();
        List<List> allStocks = new ArrayList<>();
        allStocks.add(todayStocks);
        allStocks.add(oldStocks);
        return allStocks;
    }

    @RequestMapping("/sentiment")
    public @ResponseBody
    List sentiment() {
        List<Press> pToday = _pressDAO.getToday();
        //List<Press> pYesterday = _pressDAO.getYesterday();
        Double todayS = 0.0;
        Double yesterdayS = 0.0;
        for (Press p : pToday) {
            try {
                JsonElement jElement = new JsonParser().parse(p.getSentiment());
                JsonObject jObject = jElement.getAsJsonObject();
                Double score = jObject.get("score").getAsDouble();
                todayS += score;
            } catch (NullPointerException e) {
                System.out.println(e);
            }
        }
//        for (Press p : pYesterday) {
//            yesterdayS += new Double(p.getSentiment());
//        }
        List sent = new ArrayList<>();
        Double t = todayS/pToday.size();
        sent.add(t);
        sent.add(t-.13);
//        sent.add(yesterdayS);
        //sent.add(4);
        return sent;
    }

    @RequestMapping(value = "/search")
    public @ResponseBody
    List search(@RequestParam("query") String query) {
        List<Press> p = _pressDAO.search(query);
        return p;
    }

    @RequestMapping("/percentSentiment")
    public @ResponseBody
    List percentSentiment() {
        List<Press> pToday = _pressDAO.getToday();
//        allStocks.add(todayStocks);
        return null;
    }

}
