package com.app.appbackend.registration;

import com.app.appbackend.exceptions.InvalidUserException;
import com.app.appbackend.user.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @ApiOperation("Registers new user in app")
    @PostMapping
    public User registerUser(@Valid @RequestBody User user) throws InvalidUserException {
        return registrationService.registerUser(user);
    }
}
