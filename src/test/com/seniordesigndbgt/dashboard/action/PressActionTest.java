package com.seniordesigndbgt.dashboard.action;

import com.seniordesigndbgt.dashboard.analytics.TrendAnalyzer;
import com.seniordesigndbgt.dashboard.model.Press;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by thomaspatterson on 2/28/16.
 */
public class PressActionTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetBodyContent() throws Exception {
        Press article = new Press("Reuters", "http://www.reuters.com/article/us-deutsche-bank-bafin-idUSKCN0VY2O4", "German regulator ends Deutsche Bank probes over fixing scandals", new Date(Calendar.getInstance().getTimeInMillis()));
        String body = PressAction.getBodyContent(article);
        System.out.println(body);
        assertTrue("body is there", body.length() > 100);
    }

}