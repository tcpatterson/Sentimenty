package com.seniordesigndbgt.dashboard.scheduled;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockSchedule {

    @Scheduled(fixedRate = 15000)
    public void getCurrentPrice() {
        System.out.println("test");
//        try {
//            HttpResponse<JsonNode> response = Unirest.get("http://dev.markitondemand.com/MODApis/Api/v2/Quote/jsonp?symbol=DB&callbak=x")
//                .asJson();
//            System.out.println(response.getBody());
//        } catch (UnirestException e) {
//            e.printStackTrace();
//        }
    }
}
