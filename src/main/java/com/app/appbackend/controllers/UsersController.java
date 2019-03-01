package com.app.appbackend.controllers;

import com.app.appbackend.dao.UsersDao;
import com.app.appbackend.models.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersDao usersDao;

    @ApiOperation("Gets the user with the specific id")
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Long userId) {
        return usersDao.getUserById(userId);
    }

    @ApiOperation("Updates user information")
    @PutMapping()
    public User updateUser(@RequestBody User user) {
        return usersDao.update(user);
    }

    @ApiOperation("Deletes user with specific id")
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") Long userId) {
        usersDao.delete(userId);
    }

    @ApiOperation("Deletes all users")
    @DeleteMapping
    public void delete() {
        usersDao.delete();
    }

}
