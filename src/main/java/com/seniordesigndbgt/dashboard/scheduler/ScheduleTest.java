package com.seniordesigndbgt.dashboard.scheduler;

import com.seniordesigndbgt.dashboard.analytics.AnalyzerFactory;
import com.seniordesigndbgt.dashboard.analytics.SentimentAnalyzer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTest {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        SentimentAnalyzer s = AnalyzerFactory.getSentimentAnalyzer();
//        String sent = s.getSentiment("http://www.nouse.co.uk/2016/02/16/deutsche-banks-long-fall-from-grace/", "test");
//        String sent = s.getSentiment("This is a test of the sentiment api");
//        System.out.println(sent);
        //System.out.println("The time is now " + dateFormat.format(new Date()));
    }

}
