package com.seniordesigndbgt.dashboard.controller;

import com.seniordesigndbgt.dashboard.model.Module;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.seniordesigndbgt.dashboard.model.User;
import com.seniordesigndbgt.dashboard.model.View;

import java.util.ArrayList;

@Controller
public class ViewController {

    @RequestMapping("/{username}")
    public String loadHome(@PathVariable String username, ModelMap modelMap) {
        System.out.println("username " + username);
        User current = new User(username, "employee");
        ArrayList<Module> columnOne = new ArrayList<Module>();
        ArrayList<Module> columnTwo = new ArrayList<Module>();
        ArrayList<Module> columnThree = new ArrayList<Module>();
        Module stockModule = new Module("Stocks from module", "/stocks", "fragments/stocks");
        Module trendModule = new Module("Trends from module", "/trends", "fragments/stocks");
        Module geoModule = new Module("Geo from module", "/geo", "fragments/stocks");
        Module sentiment = new Module("Sentiment from moudule", "/sentiment", "fragments/stocks");
        Module press = new Module ("Press from module", "/press", "fragments/stocks");
        Module earnings = new Module ("Earnings from module", "/earnings", "fragments/stocks");
        columnOne.add(stockModule);
        columnOne.add(sentiment);
        columnOne.add(press);
        columnTwo.add(trendModule);
        columnThree.add(geoModule);
        columnThree.add(earnings);
        View preferred = new View(current, current.getDefaultView(), columnOne, columnTwo, columnThree);
        modelMap.put("view", preferred);
        return "index";
    }

    @RequestMapping("/")
    public String loadHome(ModelMap modelMap) {
        String username = "deafult";
        System.out.println("username " + username);
        User current = new User(username, "employee");
        ArrayList<Module> columnOne = new ArrayList<Module>();
        ArrayList<Module> columnTwo = new ArrayList<Module>();
        ArrayList<Module> columnThree = new ArrayList<Module>();
        Module stockModule = new Module("DB stock information", "stock");
        Module trendModule = new Module("Currently trending about DB", "trends");
        Module sentimentModule = new Module("Current sentiment", "sentiment");
        Module gaugeModule = new Module("Current gauge", "gauge");
        columnOne.add(stockModule);
        columnTwo.add(trendModule);
        columnThree.add(sentimentModule);
        columnThree.add(gaugeModule);
        View preferred = new View(current, current.getDefaultView(), columnOne, columnTwo, columnThree);
        modelMap.put("view", preferred);
        return "index";
    }

}



