package com.app.appbackend.controllers;


import com.app.appbackend.dao.UserAuthorizationDao;
import com.app.appbackend.models.User;
import com.app.appbackend.exceptions.InvalidUserException;
import com.app.appbackend.views.UserLoginView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/")
public class RegistrationController {

    @Autowired
    UserAuthorizationDao userAuthorizationDao;



    @ApiOperation("Registers new user in app")
    @PostMapping
    public User registerUser(@Valid @RequestBody User user) {
        System.out.println(user);
//        em.persist(user);
//        return null;

        return userAuthorizationDao.register(user);
    }


    @ApiOperation("Login user into the app")
    @PostMapping("/login")
    public User loginUser(@Valid @RequestBody UserLoginView userLoginView) throws Exception {

        List<User> users = userAuthorizationDao.logIn(userLoginView);

        if (users.isEmpty()) {
            throw new InvalidUserException("Not such user", 400);
        }

        return users.get(0);
    }


}
