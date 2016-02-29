package com.seniordesigndbgt.dashboard.scheduler;


import com.seniordesigndbgt.dashboard.analytics.AnalyzerFactory;
import com.seniordesigndbgt.dashboard.analytics.TrendAnalyzer;
import com.seniordesigndbgt.dashboard.dao.PressDAO;
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
    private TwitterDAO _twitterDao = new TwitterDAO();
    TrendAnalyzer ta = new TrendAnalyzer();

    @Scheduled(fixedDelay = 10000)
    public void getTrends() {
        List<Press> pressList = _pressDao.getAll();
        String allKeywords = "";
        for (Press article : pressList) {
            allKeywords += article.getKeywords() + " ";
        }
        allKeywords = allKeywords.replaceAll("\\[", "").replaceAll("\\]","");
        List<String> keyList = ta.findKeywords(allKeywords);
        System.out.println("all keywords" + keyList.toString());
        List<Trend> trends = new ArrayList<>();
        for (String keyword : keyList) {
            String mentions = "";
            for (Press article : pressList) {
                if (article.getKeywords() != null && article.getKeywords().contains(keyword)) {
                    mentions += article.getId() + ", ";
                }
            }
            trends.add(new Trend(keyword, mentions));
        }
        for (Trend trend : trends) {
            System.out.println(trend.getTrendTitle() + "   " + trend.getMentions());
        }
    }

    @Scheduled(fixedDelay = 100000)
    public void testStringBreak() {
        String testSource = "This should split up into this, that, and the other other other other this this up up.";
        TrendAnalyzer ta = new TrendAnalyzer();
        for (String word : ta.findKeywords(testSource))
            System.out.println(word);
    }
}
