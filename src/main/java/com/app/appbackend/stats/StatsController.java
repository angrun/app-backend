package com.app.appbackend.stats;


import com.app.appbackend.user.User;
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
    StatsService statsService;

    @ApiOperation("Returns users genders count")
    @GetMapping
    public List<Long> getGenderProportion() {

        return statsService.getGenderProportion();
    }

    @ApiOperation("Return most likeable users")
    @GetMapping("/likes")
    public List<User> getUsersWithGreatestLikes() {
        return statsService.getUsersWithGreatestLikes();
    }


    @ApiOperation("Return users matching percentage")
    @GetMapping("/matchPercentage/{userId}")
    public double getUsersMatchingPercentage(@PathVariable Integer userId) {
        return statsService.getUsersMatchingPercentage(userId);
    }

    @ApiOperation("Return users matching percentage")
    @GetMapping("/userByCountry")
    public HashMap<String, Integer> getUsersByCountry() {
        return statsService.getUsersByCountry();
    }

    @ApiOperation("Return users based on hobby")
    @GetMapping("/userByHobby")
    public HashMap<String, Integer> getUsersByHobby() {
        return statsService.getUsersByHobby();
    }

    @ApiOperation("Return most talkative users")
    @GetMapping("/userByMessages")
    public HashMap<String, Integer> getUsersByMessages() {
        return statsService.getUsersByMessages();
    }

}
