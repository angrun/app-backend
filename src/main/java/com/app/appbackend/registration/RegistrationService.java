package com.app.appbackend.registration;

import com.app.appbackend.exceptions.InvalidUserException;
import com.app.appbackend.user.User;
import com.app.appbackend.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    UserAuthorizationDao userAuthorizationDao;

    @Autowired
    Validation validation;

    User registerUser(User user) throws InvalidUserException {

        validation.validateUserRegistration(user);
        return userAuthorizationDao.register(user);
    }
}
