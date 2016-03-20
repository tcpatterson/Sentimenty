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
        List<Press> pYesterday = _pressDAO.getYesterday();
        Double todayS = 0.0;
        Double yesterdayS = 0.0;
        int nullCountT = 0;
        int nullCountY = 0;
        for (Press p : pToday) {
            try {
                JsonElement jElement = new JsonParser().parse(p.getSentiment());
                JsonObject jObject = jElement.getAsJsonObject();
                Double score = jObject.get("score").getAsDouble();
                todayS += score;
            } catch (NullPointerException e) {
                nullCountT++;
            }
        }
        for (Press p : pYesterday) {
            try {
                JsonElement jElement = new JsonParser().parse(p.getSentiment());
                JsonObject jObject = jElement.getAsJsonObject();
                Double score = jObject.get("score").getAsDouble();
                yesterdayS += score;
            } catch (NullPointerException e) {
                nullCountY++;
            }
        }
        List sent = new ArrayList<>();
        todayS = todayS/(pToday.size()- nullCountT);
        yesterdayS = yesterdayS/(pYesterday.size()- nullCountY);
        sent.add(todayS);
        sent.add(todayS-yesterdayS);
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
        Double pos = 0.0;
        int posCount = 0;
        Double neg = 0.0;
        int negCount = 0;
        int nullCount = 0;
        for (Press p : pToday) {
            try {
                JsonElement jElement = new JsonParser().parse(p.getSentiment());
                JsonObject jObject = jElement.getAsJsonObject();
                Double score = jObject.get("score").getAsDouble();
                if(score > 0){
                    posCount++;
                }else{
                    negCount++;
                }
            } catch (NullPointerException e) {
                nullCount++;
            }
        }
        List sent = new ArrayList<>();
        pos = (double)posCount/(pToday.size() - nullCount) * 100.0;
        neg = (double)negCount/(pToday.size() - nullCount) * 100.0;
        sent.add(pos);
        sent.add(neg);
        return sent;
    }

}
