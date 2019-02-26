package com.app.appbackend.controllers;


import com.app.appbackend.dao.UserRegistrationDao;
import com.app.appbackend.models.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping("/")
public class RegistrationController {

    @Autowired
    UserRegistrationDao userRegistrationDao;

    @ApiOperation("Registers new user in app")
    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userRegistrationDao.register(user);
    }

}
