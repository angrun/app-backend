package com.app.appbackend.controllers;
import com.app.appbackend.dao.MatchingDao;
import com.app.appbackend.views.UserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/match")
public class MatchingController {


    @Autowired
    MatchingDao matchingDao;

    @GetMapping("/all")
    @ApiOperation("Gets all users")
    public List<UserView> getAllUsers(Integer id) {
        return matchingDao.getMatches(id);
    }


}
