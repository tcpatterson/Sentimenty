package com.seniordesigndbgt.dashboard.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seniordesigndbgt.dashboard.dao.DailyStockDAO;
import com.seniordesigndbgt.dashboard.dao.PressDAO;
import com.seniordesigndbgt.dashboard.dao.StockHistoryDAO;
import com.seniordesigndbgt.dashboard.dao.TrendDAO;
import com.seniordesigndbgt.dashboard.model.DailyStock;
import com.seniordesigndbgt.dashboard.model.Press;
import com.seniordesigndbgt.dashboard.model.StockHistory;
import com.seniordesigndbgt.dashboard.model.Trend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Table;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ApiController {

    @Autowired
    private DailyStockDAO _dailyStockDao;
    @Autowired
    private StockHistoryDAO _stockHistoryDao;
    @Autowired
    private PressDAO _pressDAO;
    @Autowired
    private TrendDAO _trendDAO;

    @RequestMapping("/stocks")
    public @ResponseBody
    List stock() {
        List<DailyStock> todayStocks = _dailyStockDao.getAll();
        List<StockHistory> oldStocks = _stockHistoryDao.getAll();
        List<List> allStocks = new ArrayList<List>();
        allStocks.add(todayStocks);
        allStocks.add(oldStocks);

//        System.out.println("==== getting stock data ====");
//        DailyStock newStock = new DailyStock("DB", LocalTime.now(), 33.45);
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
        List sent = new ArrayList<Double>();
        Double t = todayS/pToday.size();
        sent.add(t);
        sent.add(t-.13);
//        sent.add(yesterdayS);
        //sent.add(4);
        return sent;
    }

    @RequestMapping("/trends")
    public @ResponseBody
    List trend() {
        List<Trend> currentTrends = _trendDAO.getAll();
        List<String> title = new ArrayList<String>();
        List<List> mentions = new ArrayList<List>();
        for (Trend t : currentTrends) {
            title.add(t.getTrendTitle());
            String mentionsString = t.getMentions();
            String[] mentionsIds = mentionsString.split(",");
            LinkedList<Press> mentionsPerTrend = new LinkedList<Press>();
            for (String s : mentionsIds) {
                System.out.println(s);
                s = s.replace(" ","");
                System.out.println(s);
                if (s.equals(""))
                    continue;
                if (!((s.isEmpty()) || s.equals(" ") || s.equals(""))) {
                    int mentionID = Integer.parseInt(s);
                    mentionsPerTrend.add(_pressDAO.getByID(mentionID).get(0));
                }
            }
            mentions.add(mentionsPerTrend);
        }
        List<List> result = new LinkedList<List>();
        result.add(title);
        result.add(mentions);
        return result;
    }

}
