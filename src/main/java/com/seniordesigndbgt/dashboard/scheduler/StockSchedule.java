package com.seniordesigndbgt.dashboard.scheduler;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.seniordesigndbgt.dashboard.action.StockAction;
import com.seniordesigndbgt.dashboard.dao.DailyStockDAO;
import com.seniordesigndbgt.dashboard.model.DailyStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalTime;

@Component
public class StockSchedule {

    @Autowired
    private DailyStockDAO _dailyStockDao;

    //@Scheduled(cron = "0/5 9-16 * * MON-FRI")
    @Scheduled(fixedDelay = 50000)
    public void getCurrentPrice() {
        DailyStock result = null;
        try {
            HttpResponse<JsonNode> response = Unirest.get("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%27DB%27)&format=json&diagnostics=true&env=http://datatables.org/alltables.env")
                    .asJson();
            String jsonString = response.getBody().toString();

            //Walk through query to get to quote data
            JsonElement jElement = new JsonParser().parse(jsonString);
            JsonObject jObject = jElement.getAsJsonObject();
            jObject = jObject.getAsJsonObject("query");
            jObject = jObject.getAsJsonObject("results");
            jObject = jObject.getAsJsonObject("quote");
            //Get selected quote data
            JsonPrimitive priceOnly = jObject.getAsJsonPrimitive("LastTradePriceOnly");
            JsonPrimitive symbolOnly = jObject.getAsJsonPrimitive("symbol");
            double price = priceOnly.getAsDouble();
            String symbol = symbolOnly.getAsString();

//            System.out.println(price);
//            System.out.println(symbol);

            Timestamp time = new Timestamp(System.currentTimeMillis());
            result = new DailyStock(symbol, time.toLocalDateTime(), price);

            _dailyStockDao.save(result);

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
    /*
    * Get the last trade price of the day and add it to the historical data table
    * */
    //@Scheduled(cron = "0 1 17 * * MON-FRI")
    public void updateHistoricalDatabase() {

    }
    /*
     * Clears daily stock table so it can be repopulated with new data
     */
    //@Scheduled(cron = "0 59 8 * * MON-FRI")
    public void clearDailyDatabase(){
        _dailyStockDao.clearDaily();
    }
}
