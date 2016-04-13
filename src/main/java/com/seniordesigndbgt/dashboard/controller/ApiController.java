package com.seniordesigndbgt.dashboard.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seniordesigndbgt.dashboard.dao.*;
import com.seniordesigndbgt.dashboard.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private TrendDAO _trendDao;
    @Autowired
    private TwitterDAO _twitterDao;

    @RequestMapping("/stocks")
    public @ResponseBody
    List stock() {
        List<DailyStock> todayStocks = _dailyStockDao.getAll();
        List<StockHistory> oldStocks = _stockHistoryDao.getAll();
        List<List> allStocks = new ArrayList<List>();
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
        List sent = new ArrayList<List>();
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

    @RequestMapping("/mentions")
    public @ResponseBody
    List totalMentions() {
        List<Integer> mentions = new ArrayList<Integer>();
        int size = _pressDAO.getToday().size();
        mentions.add(size);
        return mentions;
    }

    @RequestMapping("/percentSentiment")
    public @ResponseBody
    String percentSentiment() {
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
        List sent = new ArrayList<List>();
        pos = (double)posCount/(pToday.size() - nullCount) * 100.0;
        neg = (double)negCount/(pToday.size() - nullCount) * 100.0;
        String results = "label,percent\n"+"positive,"+pos+"\nnegative,"+neg;
        return results;
    }

    @RequestMapping("/trends")
    public @ResponseBody
    List trend() {
        List<Trend> currentTrends = _trendDao.getMostRecent();
        List<String> title = new ArrayList<String>();
        List<List> mentions = new ArrayList<List>();
        List<Press> mentionsPerTrend = new LinkedList<Press>();
        for (Trend t : currentTrends) {
            title.add(t.getTrendTitle());
            String mentionsString = t.getMentions();
            String[] mentionsIds = mentionsString.split(",");
            for (String s : mentionsIds) {
                s = s.replace(" ","");
                if (s.equals(""))
                    continue;
                int mentionID = Integer.parseInt(s);
                mentionsPerTrend.add(_pressDAO.getById(mentionID).get(0));
            }
            LinkedList<Press> mentionsCopy = new LinkedList<Press>(mentionsPerTrend);
            mentions.add(mentionsCopy);
            mentionsPerTrend.clear();
        }
        List<List> result = new LinkedList<List>();

        result.add(title);
        result.add(mentions);
        return result;
    }

    @RequestMapping("/twitter")
    @ResponseBody
    public List twitter() {
        int numOfTwitters = 250;
        List<Twitter> twitterList = _twitterDao.getAll();
        if (twitterList.size() > numOfTwitters)
            return twitterList.subList(0,numOfTwitters);
        else
            return twitterList;
    }

}
