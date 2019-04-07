package com.app.appbackend.controllers;


import com.app.appbackend.dao.StatsDao;
import com.app.appbackend.models.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {


    @Autowired
    StatsDao statsDao;

    @ApiOperation("Returns users genders count")
    @GetMapping
    public List<Long> getGenderProportion() {

        return statsDao.getGenderEquality();
    }

    @ApiOperation("Return most likeable users")
    @GetMapping("/likes")
    public List<User> getUsersWithGreatestLikes() {
        return statsDao.getUsersWithGreatestLikes();
    }


    @ApiOperation("Return users matching percentage")
    @GetMapping("/matchPercentage/{userId}")
    public double getUsersMatchingPercentage(@PathVariable Integer userId) {
        return statsDao.getMatchPercentage(userId);
    }

    @ApiOperation("Return users matching percentage")
    @GetMapping("/userByCountry")
    public HashMap<String, Integer> getUsersByCountry() {
        return statsDao.getUsersByCountry();
    }

}
