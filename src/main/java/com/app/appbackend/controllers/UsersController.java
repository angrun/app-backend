package com.app.appbackend.controllers;

import com.app.appbackend.dao.ImageDao;
import com.app.appbackend.dao.UsersDao;
import com.app.appbackend.exceptions.InvalidUserException;
import com.app.appbackend.models.User;
import com.app.appbackend.security.JwtDecoder;
import com.app.appbackend.views.UserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersDao usersDao;

    @Autowired
    ImageDao imageDao;

    private JwtDecoder decoder = new JwtDecoder();

    @ApiOperation("Gets the user with the specific id")
    @GetMapping(produces = "application/json")
    public UserView getUser(@RequestHeader(value="Authorization") String authorization) throws IOException {
        return usersDao.getUserByEmail(decoder.getEmailFromToken(authorization));
    }

    @ApiOperation("Updates user information")
    @PutMapping()
    public User updateUser(@RequestBody UserView userView) {
        return usersDao.update(userView);
    }

    @ApiOperation("Updates user information")
    @PostMapping("images")
    public void createFile(@RequestParam("file") MultipartFile file,
                           @RequestHeader(value="Authorization") String authorization,
                           RedirectAttributes redirectAttributes) throws InvalidUserException {
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

}
