package com.seniordesigndbgt.dashboard.scheduler;

import com.seniordesigndbgt.dashboard.dao.PressDAO;
import com.seniordesigndbgt.dashboard.model.Press;
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

    private SentimentAnalyzer analyzer;

    private static final int RATE = 1000000;

    @Scheduled(fixedRate = RATE)
    public void checkBloomberg() throws IOException {
        Document doc = Jsoup.connect("http://www.bloomberg.com/search?query=deutsche+bank").get();
        Elements newsHeadlines = doc.select(".search-result-story__headline");
        analyzer = SentimentAnalyzer.getInstance();
        for(Element e : newsHeadlines) {
            String link = e.child(0).attr("href");
            link = "http://www.bloomberg.com/" + link;
            String title = e.text();
            try {
                Press article = new Press("Bloomberg", link, title);
                _pressDao.save(article);
                article.setSentiment(analyzer.getSentiment(article));
                _pressDao.update(article);
            }catch(Exception error){
                
            }
        }
    }

    @Scheduled(fixedRate = RATE)
    public void checkForbes() throws IOException {
        Document doc = Jsoup.connect("http://www.forbes.com/search/?q=deutsche+bank").get();
        Elements newsHeadlines = doc.select(".article");
        analyzer = SentimentAnalyzer.getInstance();
        for(Element e : newsHeadlines) {
            String link = e.child(0).attr("href");
            link = "http://www.forbes.com/" + link;
            String title = e.text();
            try {
                Press article = new Press("Forbes", link, title);
                _pressDao.save(article);
                article.setSentiment(analyzer.getSentiment(article));
                _pressDao.update(article);
            }catch(Exception error){

            }
        }
    }

    @Scheduled(fixedRate = RATE)
    public void checkNyt() throws IOException {
        Document doc = Jsoup.connect("http://query.nytimes.com/search/sitesearch/?pgtype=Homepage#/deutsche bank").get();
        Elements newsHeadlines = doc.select(".story");
        analyzer = SentimentAnalyzer.getInstance();
        for(Element e : newsHeadlines) {
            String link = e.child(0).attr("href");
            link = "http://www.nytimes.com/" + link;
            String title = e.text();
            try {
                Press article = new Press("NY Times", link, title);
                _pressDao.save(article);
                article.setSentiment(analyzer.getSentiment(article));
                _pressDao.update(article);
            }catch(Exception error){

            }
        }
    }

    @Scheduled(fixedRate = RATE)
    public void checkReuters() throws IOException {
        Document doc = Jsoup.connect("http://www.reuters.com/search/news?blob=deutsche+bank").get();
        Elements newsHeadlines = doc.select(".search-result-title");
        analyzer = SentimentAnalyzer.getInstance();
        for(Element e : newsHeadlines) {
            String link = e.child(0).attr("href");
            String title = e.text();
            try {
                Press article = new Press("Reuters", link, title);
                _pressDao.save(article);
                article.setSentiment(analyzer.getSentiment(article));
                _pressDao.update(article);
            }catch(Exception error){

            }
        }
    }

}
