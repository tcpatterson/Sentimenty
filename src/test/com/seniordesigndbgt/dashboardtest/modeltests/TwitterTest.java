package com.seniordesigndbgt.dashboardtest.modeltests;

import org.junit.Test;
import com.seniordesigndbgt.dashboard.model.Twitter;
import static org.junit.Assert.*;

/**
 * Created by kamehardy on 3/27/16.
 */
public class TwitterTest {

    @Test
    public void testGetId() throws Exception {
        long val = 100000;
        Twitter tweet = new Twitter();
        tweet.setId(val);
        assertEquals(val, tweet.getId());
    }


    @Test
    public void testGetText() throws Exception {
        String str = "Hello world";
        Twitter tweet = new Twitter();
        tweet.setText(str);
        assertEquals(str, tweet.getText());
    }

    @Test
    public void testToString() throws Exception {
        Twitter tweet = new Twitter("dbcares", "hello world", "");
        assertEquals("@dbcares - hello world", tweet.toString());
    }
}