package com.app.appbackend.controllers;

import com.app.appbackend.dao.UsersDao;
import com.app.appbackend.dao.ImageDao;
import com.app.appbackend.models.User;
import com.app.appbackend.views.UserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@CrossOrigin()
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersDao usersDao;

    @Autowired
    ImageDao imageDao;

    @ApiOperation("Gets the user with the specific id")
    @GetMapping(value = "/{userId}", produces = "application/json")
    public UserView getUserById(@PathVariable("userId") Long userId) throws IOException {
        return usersDao.getUserById(userId);
    }

    @ApiOperation("Updates user information")
    @PutMapping()
    public User updateUser(@RequestBody UserView userView) {
        return usersDao.update(userView);
    }

    @ApiOperation("Updates user information")
    @PostMapping("images")
    public void createFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            imageDao.createImage(file);
        } catch (IOException e) {
            System.out.println("FAIL");
        }
    }

    @ApiOperation("Updates user information")
    @GetMapping("images")
    public Resource createFile() {
            return imageDao.findOneImage("test");
    };





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
