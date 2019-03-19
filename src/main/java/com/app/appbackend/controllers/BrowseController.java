package com.app.appbackend.controllers;


import com.app.appbackend.dao.BrowseDao;
import com.app.appbackend.models.Matching;
import com.app.appbackend.models.User;
import com.app.appbackend.views.UserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/browse")
public class BrowseController {

    @Autowired
    BrowseDao browseDao;

    @GetMapping("/all")
    @ApiOperation("Gets all users")
    public List<UserView> getAllUsers() {
        return browseDao.getAllUsers();
    }


    @GetMapping("/filter")
    @ApiOperation("Gets filtered users")
    public List<User> getFilteredUsers(String city, String country, String gender) {
        return browseDao.getFilteredUsers(city, country, gender);
    }

    @PostMapping("/grade")
    @ApiOperation("Matching certain user")
    public void likeUser(Matching matching) {
        browseDao.gradeUser(matching);
    }
}
