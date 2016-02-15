package com.seniordesigndbgt.dashboard.scheduled;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.seniordesigndbgt.dashboard.model.DailyStock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class StockSchedule {

    @Scheduled(cron = "* 0/5  9-17 * * MON-FRI")
    public DailyStock getCurrentPrice() {
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

            System.out.println(price);
            System.out.println(symbol);
            
            result = new DailyStock(symbol, LocalTime.now(), price);


            //TODO - Link DailyStock object with Hibernate
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        //Can check if result is null
        return result;
    }
    /*
    * Get the last trade price of the day and add it to the historical data table
    * */
    @Scheduled(cron = "0 1 17 * * MON-FRI")
    public void updateHistoricalDatabase() {

    }

    @Scheduled(cron = "0 59 8 * * MON-FRI")
    public void clearDailyDatabase(){

    }
}
