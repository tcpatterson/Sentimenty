package com.seniordesigndbgt.dashboard.analytics;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.DoubleHolder;

public class SentimentAnalyzerTest extends TestCase{
    private SentimentAnalyzer analyzer;
    @Before
    public void setUp() throws Exception {
        analyzer = SentimentAnalyzer.getInstance();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetSentiment() throws Exception {
        String testNeg = "I fucking hate this.";
        String testPos = "This is so awesome!";
        String negRes = analyzer.getSentiment(testNeg);
        String posRes = analyzer.getSentiment(testPos);
        JsonElement jElementNeg = new JsonParser().parse(negRes);
        JsonElement jElementPos = new JsonParser().parse(negRes);
        JsonObject jObject = jElementNeg.getAsJsonObject();
        jObject = jObject.getAsJsonObject("score");
        Double n = jObject.getAsDouble();
        assertTrue("n between -1 and 0", n<0.00 && n>-1.00);
    }

    @Test
    public void testGetSentiment1() throws Exception {

    }
}
