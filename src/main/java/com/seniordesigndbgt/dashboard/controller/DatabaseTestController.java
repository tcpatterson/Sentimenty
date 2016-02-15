package com.seniordesigndbgt.dashboard.controller;

import com.seniordesigndbgt.dashboard.dao.DailyStockDAO;
import com.seniordesigndbgt.dashboard.dao.UserDAO;
import com.seniordesigndbgt.dashboard.dao.ViewDAO;
import com.seniordesigndbgt.dashboard.model.DailyStock;
import com.seniordesigndbgt.dashboard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;
import java.util.List;

@Controller
public class DatabaseTestController {

    @Autowired
    private UserDAO _userDao;
    @Autowired
    private ViewDAO _viewDao;
    @Autowired
    private DailyStockDAO _dailyStockDao;

    @RequestMapping("/newUser/{username}")
    @ResponseBody
    public String save(@PathVariable String username) {
        try {
            User user = new User(username);
            _userDao.save(user);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "User saved successfully";
    }

    @RequestMapping("/show/{username}")
    @ResponseBody
    public String show(@PathVariable String username) {
        User user;
        List views = null;
        try {
            user = _userDao.getByUsername(username);
        } catch (Exception e) {
            return "User not found";
        }
        try {
            views = _viewDao.getByUser(user);
        } catch (Exception e) {

        }

        String size = "";

        if(views != null){
            size +=  "" + views.size();
        }

        return "username " + user.getUsername() + " DefaultView " + user.getDefaultView() + " views " + size;

    }

    @RequestMapping("/test/showDailyStocks")
    @ResponseBody
    public String showStocks(){
        DailyStock test1 = new DailyStock("DB", LocalTime.now(), 100.01);
        DailyStock test2 = new DailyStock("DB", LocalTime.now(), 101.01);
        DailyStock test3 = new DailyStock("DB", LocalTime.now(), 102.01);

        _dailyStockDao.save(test1);
        _dailyStockDao.save(test2);
        _dailyStockDao.save(test3);

        return "Stocks: " + test1.getValue() + " " + test2.getValue() + " " + test3.getValue();

    }
}
