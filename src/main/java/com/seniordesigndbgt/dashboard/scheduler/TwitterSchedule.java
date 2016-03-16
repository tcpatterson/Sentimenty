package com.seniordesigndbgt.dashboard.scheduler;

import com.seniordesigndbgt.dashboard.analytics.TrendAnalyzer;
import com.seniordesigndbgt.dashboard.dao.TwitterDAO;
import com.seniordesigndbgt.dashboard.model.*;
import com.seniordesigndbgt.dashboard.model.Twitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


import java.io.IOException;
import java.util.List;

@Component
public class TwitterSchedule {

    @Autowired
    private TwitterDAO _twitterDao;

    @Scheduled (fixedDelay = 5000)
    public void gatherTwitter()
    {
        String hashtag = "deutschebank";
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("VsSe2zAN5w17HWNUACGCvofrk")
                    .setOAuthConsumerSecret("1c5ZM9J7JL2aEHHSVzsDppN2o9c0reTGIclz7dyMRiGWwmaA7P")
                    .setOAuthAccessToken("2861869233-GUXY7NBpokrCdSX3Ca5avMUyMM5fxfvFLqynzpw")
                    .setOAuthAccessTokenSecret("r5LyXwCnFvQMwHe0dZPoIpDw91EfiRI9gndxgsmDVOL0Y");
            TwitterFactory tf = new TwitterFactory(cb.build());
            twitter4j.Twitter twitter = tf.getInstance();

            Query query = new Query(hashtag);
            query.count(10);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()){
//                if (!status.getLang().equals(null) && status.getLang().equalsIgnoreCase("en")) {
//                    System.out.println("@" + status.getUser().getName() + " - " + status.getText());
//                    if (status.getGeoLocation() != null) {
//                        System.out.println(status.getGeoLocation().toString());
//                    }
//                    System.out.println(status.getCreatedAt().toString());
//                }
                if (!status.getLang().equals(null) && status.getLang().equalsIgnoreCase("en")) {
                    String tweetText = status.getText();
                    String author = status.getUser().getName();
                    TrendAnalyzer ta = new TrendAnalyzer();
                    String keywords = ta.findKeywords(tweetText);
                    Twitter t = new Twitter(author, tweetText, keywords);
                    _twitterDao.save(t);
                }
            }
        } catch (TwitterException e){
            e.printStackTrace();
        }
    }

}