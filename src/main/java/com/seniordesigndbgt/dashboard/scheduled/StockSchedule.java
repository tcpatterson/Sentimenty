package com.seniordesigndbgt.dashboard.scheduled;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.seniordesigndbgt.dashboard.model.Stock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class StockSchedule {

    @Scheduled(fixedRate = 15000)
    public void getCurrentPrice() {
        System.out.println("test");
        Gson g = new Gson();
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
            
            Stock result = new Stock(symbol, LocalTime.now(), price);

            //TODO - Link Stock object with Hibernate
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
