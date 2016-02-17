package com.seniordesigndbgt.dashboard.scheduler;

import com.seniordesigndbgt.dashboard.dao.PressDAO;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.seniordesigndbgt.dashboard.analytics.SentimentAnalyzer;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PressSchedule {

    @Autowired
    private PressDAO _pressDao;

    @Scheduled(fixedRate = 10000)
    public void checkBloomberg() throws IOException {
        Document doc = Jsoup.connect("http://www.bloomberg.com/search?query=deutsche+bank").get();
        Elements newsHeadlines = doc.select(".search-result-story__headline");
        for(Element e : newsHeadlines) {
            System.out.println(e.text());
        }
    }

    @Scheduled(fixedRate = 10000)
    public void checkForbes() throws IOException {
        Document doc = Jsoup.connect("http://www.forbes.com/search/?q=deutsche+bank").get();
        Elements newsHeadlines = doc.select(".article");
        for(Element e : newsHeadlines) {
            System.out.println(e.text());
        }
    }

    @Scheduled(fixedRate = 10000)
    public void checkNyt() throws IOException {
        Document doc = Jsoup.connect("http://query.nytimes.com/search/sitesearch/?pgtype=Homepage#/deutsche bank").get();
        Elements newsHeadlines = doc.select(".story");
        for(Element e : newsHeadlines) {
            System.out.println(e.text());
        }
    }

    @Scheduled(fixedRate = 10000)
    public void checkReuters() throws IOException {
        Document doc = Jsoup.connect("http://www.reuters.com/search/news?blob=deutsche+bank").get();
        Elements newsHeadlines = doc.select(".search-result-title");
        for(Element e : newsHeadlines) {
            System.out.println(e.text());
        }
    }

}
