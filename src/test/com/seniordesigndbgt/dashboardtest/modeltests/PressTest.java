package com.seniordesigndbgt.dashboardtest.modeltests;

import org.junit.Test;
import com.seniordesigndbgt.dashboard.model.Press;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Created by kamehardy on 3/27/16.
 */
public class PressTest {

    @Test
    public void testGetId() throws Exception {
        Press p = new Press();
        p.setId(100);
        assertEquals(100, p.getId());
    }

    @Test
    public void testGetSource() throws Exception {
        Press p = new Press();
        p.setSource("str");
        assertEquals("str", p.getSource());
    }

    @Test
    public void testGetUrl() throws Exception {
        Press p = new Press();
        p.setUrl("www.bloomberg.com");
        assertEquals("www.bloomberg.com", p.getUrl());
    }

    @Test
    public void testGetTitle() throws Exception {
        Press p = new Press();
        p.setTitle("Article");
        assertEquals("Article", p.getTitle());
    }


    @Test
    public void testGetSentiment() throws Exception {
        Press p = new Press();
        p.setSentiment("Positive");
        assertEquals("Positive", p.getSentiment());
    }

    @Test
    public void testGetKeywords() throws Exception {
        Press p = new Press();
        p.setKeywords("art");
        assertEquals("art", p.getKeywords());
    }


    @Test
    public void testGetTime() throws Exception {
        Press p = new Press();
        Date today = new Date();
        p.setTime(today);
        assertEquals(today, p.getTime());
    }

}