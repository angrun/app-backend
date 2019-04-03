package com.app.appbackend.controllers;


import com.app.appbackend.dao.BrowseDao;
import com.app.appbackend.models.Matching;
import com.app.appbackend.security.JwtDecoder;
import com.app.appbackend.views.FilterView;
import com.app.appbackend.views.UserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/browse")
public class BrowseController {

    @Autowired
    BrowseDao browseDao;

    private JwtDecoder decoder = new JwtDecoder();

    @GetMapping("/all")
    @ApiOperation("Gets all users")
    public List<UserView> getAllUsers(@RequestHeader(value="Authorization") String authorization,
                                      FilterView filterView) {
        return browseDao.getAllUsers(filterView, decoder.getEmailFromToken(authorization));
    }


//    @GetMapping("/filter")
//    @ApiOperation("Gets filtered users")
//    public List<User> getFilteredUsers(String city, String country, String gender) {
//        return browseDao.getFilteredUsers(city, country, gender);
//    }

    @PostMapping("/grade")
    @ApiOperation("Matching certain user")
    public void likeUser(@RequestBody Matching matching) {
        browseDao.gradeUser(matching);
    }
}
