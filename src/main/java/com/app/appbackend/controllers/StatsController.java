package com.app.appbackend.controllers;


import com.app.appbackend.dao.StatsDao;
import com.app.appbackend.models.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/stats")
public class StatsController {


    @Autowired
    StatsDao statsDao;

    @ApiOperation("Returns users genders count")
    @GetMapping
    public List<Long> getGenderEquality() {

        return statsDao.getGenderEquality();
    }


    @ApiOperation("Return most likeable users")
    @GetMapping("/likes")
    public List<User> getUsersWithGreatestLikes() {
        return statsDao.getUsersWithGreatestLikes();
    }


}
