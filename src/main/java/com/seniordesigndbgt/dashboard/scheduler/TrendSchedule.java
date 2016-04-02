package com.seniordesigndbgt.dashboard.scheduler;


import com.seniordesigndbgt.dashboard.analytics.AnalyzerFactory;
import com.seniordesigndbgt.dashboard.analytics.TrendAnalyzer;
import com.seniordesigndbgt.dashboard.dao.PressDAO;
import com.seniordesigndbgt.dashboard.dao.TrendDAO;
import com.seniordesigndbgt.dashboard.dao.TwitterDAO;
import com.seniordesigndbgt.dashboard.model.Press;
import com.seniordesigndbgt.dashboard.model.Trend;
import com.seniordesigndbgt.dashboard.model.Twitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TrendSchedule {

    @Autowired
    private PressDAO _pressDao;
    @Autowired
    private TwitterDAO _twitterDao;
    @Autowired
    private TrendDAO _trendDao;
    TrendAnalyzer ta = new TrendAnalyzer();

    @Scheduled(fixedDelay = 10000)
    public void getTrends() {
        //System.out.println("\nStart");
        List<Press> pressList = _pressDao.getAll();
        String allKeywords = "";
        for (Press article : pressList) {
            if (article.getKeywords() != null) {
                String[] articleKeywordSplit = article.getKeywords().split(",");
                allKeywords += article.getKeywords() + " ";
                for (String keyword : articleKeywordSplit) {
                    allKeywords += keyword + " ";
                }
            }
        }
        allKeywords = allKeywords.replace(",", " ");
        String keyString = ta.findKeywords(allKeywords);
        //System.out.println("all keywords: " + keyString);
        String[] keywordSplit = keyString.split(",");
        List<Trend> trends = new ArrayList<Trend>();
        for (String keyword : keywordSplit) {
            if (!keyword.isEmpty()) {
                String mentions = "";
                for (Press article : pressList) {
                    if (article.getKeywords() != null && article.getKeywords().contains(keyword)) {
                        mentions += article.getId() + ", ";
                    }
                }
                _trendDao.save(new Trend(keyword, mentions));
            }
        }
        for (Trend trend : trends) {

            //System.out.println(trend.getTrendTitle() + "   " + trend.getMentions());
        }
//        ta.findNewTrends();
    }

//    @Scheduled(fixedDelay = 10000)
    public void testStringBreak() {
        String testSource = "This should split up into this, that, and the other other other other this this up up.";
        TrendAnalyzer ta = new TrendAnalyzer();
        //System.out.println(ta.findKeywords(testSource));
    }
}
