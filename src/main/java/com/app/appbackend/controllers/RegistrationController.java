package com.app.appbackend.controllers;


import com.app.appbackend.dao.UserAuthorizationDao;
import com.app.appbackend.exceptions.InvalidUserException;
import com.app.appbackend.models.User;
import com.app.appbackend.validation.Validation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UserAuthorizationDao userAuthorizationDao;

    @Autowired
    Validation validation;



    @ApiOperation("Registers new user in app")
    @PostMapping
    public User registerUser(@Valid @RequestBody User user) throws InvalidUserException {

        validation.validateUserRegistration(user);
        return userAuthorizationDao.register(user);
    }
}
