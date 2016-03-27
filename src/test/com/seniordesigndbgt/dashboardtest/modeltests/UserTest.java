package com.seniordesigndbgt.dashboardtest.modeltests;

import org.junit.Test;
import com.seniordesigndbgt.dashboard.model.User;
import static org.junit.Assert.*;

/**
 * Created by kamehardy on 3/27/16.
 */
public class UserTest {

    @Test
    public void testGetUsername() throws Exception {
        String str = "dbcares";
        User u = new User();
        u.setUsername(str);
        assertEquals(str, u.getUsername());

    }


    @Test
    public void testGetDefaultView() throws Exception {
        String str = "View1";
        User u = new User();
        u.setDefaultView(str);
        assertEquals(str, u.getDefaultView());
    }

}