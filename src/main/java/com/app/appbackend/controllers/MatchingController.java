package com.app.appbackend.controllers;
import com.app.appbackend.dao.MatchingDao;
import com.app.appbackend.security.JwtDecoder;
import com.app.appbackend.views.UserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchingController {


    @Autowired
    MatchingDao matchingDao;

    private JwtDecoder decoder = new JwtDecoder();

    @GetMapping("/all")
    @ApiOperation("Gets all users")
    public List<UserView> getAllUsers(@RequestHeader(value="Authorization") String authorization) {
        return matchingDao.getMatches(decoder.getEmailFromToken(authorization));
    }
}
