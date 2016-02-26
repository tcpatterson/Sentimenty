package com.seniordesigndbgt.dashboard.analytics;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.DoubleHolder;

public class TrendAnalyzerTest extends TestCase{

    TrendAnalyzer ta = new TrendAnalyzer();
    @org.junit.Before
    public void setUp() throws Exception {
        ta.updateFrequencyMap("one two two three three three four four four four ");
    }

    @org.junit.Test
    public void testFindNewTrends() throws Exception {
        assertTrue(ta.getNewTrends() == null);
        ta.findNewTrends();
        List <Map.Entry<String,Integer>> newTrendsComparator = ta.getNewTrends();
        int countComparator = 4;
        for (Map.Entry<String, Integer> entry : newTrendsComparator){
            assertEquals(entry.getValue, countComparator--);
        }
    }

    @org.junit.Test
    public void testRefreshShortMap() throws Exception {
        ta.refreshShortMap();
        assertTrue(ta.getShortFrequencyMap().getEntrySet() == null);
    }

    @org.junit.Test
    public void testGetShortFrequencyMap() throws Exception {
        Map<String, Integer> freqMap = ta.getShortFrequencyMap();
        assertTrue(freqMap.containsKey("one"));
        assertTrue(freqMap.containsKey("two"));
        assertTrue(freqMap.containsKey("three"));
        assertTrue(freqMap.containsKey("four"));
    }

    @org.junit.Test
    public void testGet3MaxValues() throws Exception {
        List<Map.Entry<String,Integer>> topThree = ta.get3MaxValues(ta.getShortFrequencyMap());
        int countCheck = 4;
        for ( Map.Entry<String, Integer> entry : topThree){
            assertEquals(entry.getValue(), countCheck--);
        }
    }

    @org.junit.Test
    public void testUpdateFrequencyMap() throws Exception {
        ta.updateFrequencyMap("one two two three three three four four four four ");
        List<Map.Entry<String,Integer>> topThree = ta.get3MaxValues(ta.getShortFrequencyMap());
        int countCheck = 8;
        for ( Map.Entry<String, Integer> entry : topThree){
            assertEquals(entry.getValue(), countCheck);
            countCheck -= 2;
        }
    }

    @org.junit.After
    public void tearDown() throws Exception {
        ta.refreshShortMap();
    }
}
