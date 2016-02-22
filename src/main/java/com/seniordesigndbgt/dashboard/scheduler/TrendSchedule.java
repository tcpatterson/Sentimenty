package com.seniordesigndbgt.dashboard.scheduler;

import com.seniordesigndbgt.dashboard.analytics.TrendAnalyzer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
public class TrendSchedule {

    TrendAnalyzer testAnalyzer = new TrendAnalyzer();
    @Scheduled(fixedDelay = 10000)
    public void testStringBreak(){
        String testSource = "This should split up into this, that, and the other other other other this this up up.";
        System.out.println(testSource);
        Map<String, Integer> map = testAnalyzer.getWordMap(testSource);

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
