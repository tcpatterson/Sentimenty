package com.seniordesigndbgt.dashboard.analytics;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.seniordesigndbgt.dashboard.model.Press;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SentimentAnalyzer {

    public SentimentAnalyzer() {}
    private final String apiKey = "329f2c9dac59bb494f29ef219b42d84b6896be5c";
    private final String apiBase = "http://gateway-a.watsonplatform.net/calls";

    public String getSentiment(String text) {
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpResponse<JsonNode> response = null;
        try {
            String url = apiBase + "/text/TextGetTextSentiment?apikey=" + apiKey + "&text=" + text + "&outputMode=json";
            response = Unirest.get(url)
                    .asJson();
            String jsonString = response.getBody().toString();

            //Walk through query to get to quote data
            JsonElement jElement = new JsonParser().parse(jsonString);
            JsonObject jObject = jElement.getAsJsonObject();
            jObject = jObject.getAsJsonObject("docSentiment");

            String out = jObject.toString();
            return out;
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    public String getSentiment(String one, String two) {
        // String url = pressArticle.getUrl();
        //TODO
        String pressUJrl = "www.google.com";
        pressUJrl = one;
        HttpResponse<JsonNode> response = null;
        try {
            String url = apiBase + "/url/URLGetTextSentiment?url=" + pressUJrl + "&apikey=" + apiKey + "&outputMode=json&sourceText=cleaned";
            response = Unirest.get(url)
                    .asJson();
            String jsonString = response.getBody().toString();

            //Walk through query to get to quote data
            JsonElement jElement = new JsonParser().parse(jsonString);
            JsonObject jObject = jElement.getAsJsonObject();
            jObject = jObject.getAsJsonObject("docSentiment");

            String out = jObject.toString();
            return out;
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
