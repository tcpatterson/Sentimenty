package com.seniordesigndbgt.dashboard.controller;

import com.seniordesigndbgt.dashboard.dao.UserDAO;
import com.seniordesigndbgt.dashboard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DatabaseTestController {

    @Autowired
    private UserDAO _userDao;

    @RequestMapping("/newUser/{username}")
    @ResponseBody
    public String save(String username) {
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
    public String show(String username) {
        User user;
        try {
            user = _userDao.getByUsername(username);
        } catch (Exception e) {
            return "User not found";
        }
        return user.getDefaultView();

    }
}
