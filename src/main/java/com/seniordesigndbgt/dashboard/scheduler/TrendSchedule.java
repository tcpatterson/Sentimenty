package com.seniordesigndbgt.dashboard.scheduler;

/**
import com.seniordesigndbgt.dashboard.dao.PressDAO;
import com.seniordesigndbgt.dashboard.dao.TwitterDAO;
import com.seniordesigndbgt.dashboard.model.Press;
import com.seniordesigndbgt.dashboard.model.Twitter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class TrendSchedule {
    private PressDAO _pressDao;
    private TwitterDAO _twitterDao;
    TrendAnalyzer testAnalyzer = new TrendAnalyzer();
//    @Scheduled(fixedDelay = 10000)
//    public void testStringBreak(){
//        String testSource = "This should split up into this, that, and the other other other other this this up up.";
//        testAnalyzer.refreshShortMap();
//        testAnalyzer.refreshLongMap();
//        testAnalyzer.updateFrequencyMap(testSource);
//        testAnalyzer.findNewTrends();
//
//    }
    @Scheduled(fixedDelay = 2000)
    public void getTrendFromArticles(){
        //get all text, updateFrequencyMap, findNewTrends()
        //Should probably print out frequency map for each update call, to get feel for threshold
//        List<Press> articles = _pressDao.getAll();
        List<Twitter> tweets = _twitterDao.getAll();
        for (Twitter tweet : tweets){
            testAnalyzer.updateFrequencyMap(tweet.getText());
        }
    }
}
