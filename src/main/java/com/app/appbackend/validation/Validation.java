package com.app.appbackend.validation;

import com.app.appbackend.exceptions.InvalidUserException;
import com.app.appbackend.models.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class Validation {

    @PersistenceContext
    public EntityManager em;

    public void validateUserRegistration(User user) throws InvalidUserException {

        TypedQuery<User> query1 = em.createQuery("select u from User u where u.email = :email", User.class);
        query1.setParameter("email", user.getEmail());

        if (!query1.getResultList().isEmpty()) {
            throw new InvalidUserException("Person with such email already exists", 400);
        }
        if (!user.getPassword().equals(user.getPassword2())) {
            throw new InvalidUserException("Passwords do not match", 400);
        }
    }

    public void validateUserLogin(List<User> users) throws InvalidUserException {

        if (users.isEmpty()) {
            throw new InvalidUserException("Not such user", 400);
        }

    }


}
