package com.seniordesigndbgt.dashboard.scheduler;


import com.seniordesigndbgt.dashboard.analytics.AnalyzerFactory;
import com.seniordesigndbgt.dashboard.analytics.TrendAnalyzer;
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
    private TwitterDAO _twitterDao = new TwitterDAO();
    @Scheduled(fixedDelay = 10000)
    public void testStringBreak() {
        String testSource = "This should split up into this, that, and the other other other other this this up up.";
        TrendAnalyzer ta = new TrendAnalyzer();
        System.out.println(ta.findKeywords(testSource));
    }
}
