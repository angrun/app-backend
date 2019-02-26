package com.app.appbackend.controllers;


import com.app.appbackend.dao.StatsDao;
import com.app.appbackend.dao.UserRegistrationDao;
import com.app.appbackend.models.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/stats")
public class StatsController {


    @Autowired
    StatsDao statsDao;

    @ApiOperation("Returns users genders count")
    @GetMapping
    public void registerUser(@RequestBody User user) {

        System.out.println(statsDao.getGenderEquality());
    }


}
