package com.seniordesigndbgt.dashboard.analytics;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import junit.framework.TestCase;
import org.aspectj.runtime.reflect.Factory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.DoubleHolder;
import com.seniordesigndbgt.dashboard.analytics.SentimentAnalyzer;
import com.seniordesigndbgt.dashboard.analytics.AnalyzerFactory;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;

public class SentimentAnalyzerTest extends TestCase{
    private SentimentAnalyzer analyzer;
    @Before
    public void setUp() throws Exception {
        analyzer = AnalyzerFactory.getSentimentAnalyzer();
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
        JsonElement jElementPos = new JsonParser().parse(posRes);
        JsonObject jObject = jElementNeg.getAsJsonObject();
        JsonPrimitive jsonPrimitive = jObject.getAsJsonPrimitive("score");
        Double n = jsonPrimitive.getAsDouble();
        assertTrue("n between -1 and 0", n<0.00 && n>-1.00);
        jObject = jElementPos.getAsJsonObject();
    }

    @Test
    public void testGetSentiment1() throws Exception {

    }
}
