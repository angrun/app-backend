package com.app.appbackend.controllers;


import com.app.appbackend.dao.UserRegistrationDao;
import com.app.appbackend.models.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("/")
public class RegistrationController {

    @Autowired
    UserRegistrationDao userRegistrationDao;


    @ApiOperation("Registers new user in app")
    @PostMapping
    public User registerUser(@Valid @RequestBody User user) throws Exception {
        System.out.println(user);
//        em.persist(user);
//        return null;

        return userRegistrationDao.register(user);
    }


}
