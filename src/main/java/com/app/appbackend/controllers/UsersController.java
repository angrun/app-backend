package com.app.appbackend.controllers;

import com.app.appbackend.dao.ImageDao;
import com.app.appbackend.dao.UsersDao;
import com.app.appbackend.exceptions.InvalidUserException;
import com.app.appbackend.models.User;
import com.app.appbackend.security.JwtDecoder;
import com.app.appbackend.validation.Validation;
import com.app.appbackend.views.UserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersDao usersDao;

    @Autowired
    ImageDao imageDao;

    @Autowired
    Validation validation;

    private JwtDecoder decoder = new JwtDecoder();

    @ApiOperation("Gets the user with the specific id")
    @GetMapping(produces = "application/json")
    public UserView getUser(@RequestHeader(value="Authorization") String authorization)  {
        return usersDao.getUserByEmail(decoder.getEmailFromToken(authorization));
    }

    @ApiOperation("Updates user information")
    @PutMapping()
    public User updateUser(@RequestBody @Valid UserView userView) throws InvalidUserException {

        validation.emailExists(userView.getEmail());
        return usersDao.update(userView);
    }

    @ApiOperation("Updates user information")
    @PostMapping("images")
    public void createFile(@RequestParam("file") MultipartFile file,
                           @RequestHeader(value="Authorization") String authorization) throws InvalidUserException {
        try {
            imageDao.createImage(file, decoder.getEmailFromToken(authorization));
        } catch (IOException e) {
            System.out.println("FAIL");
        }
    }

    @ApiOperation("Updates user information")
    @GetMapping("images")
    public Resource createFile() {
            return imageDao.findOneImage("test");
    }


//    @ApiOperation("Deletes user with specific id")
//    @DeleteMapping("/{userId}")
//    public void deleteUserById(@PathVariable("userId") Long userId) {
//        usersDao.delete(userId);
//    }
//
//    @ApiOperation("Deletes all users")
//    @DeleteMapping
//    public void delete() {
//        usersDao.delete();
//    }

}
