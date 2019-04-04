package com.app.appbackend.validation;

import com.app.appbackend.exceptions.InvalidUserException;
import com.app.appbackend.models.User;
import com.app.appbackend.utils.Utils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validation {

    @PersistenceContext
    public EntityManager em;

    private Pattern pattern;
    private Matcher matcher;

    private static final String IMAGE_PATTERN =
            "([^\\s]+(\\.(?i)(jpg|png|jpeg))$)";

    public void validateUserRegistration(User user) throws InvalidUserException {

        TypedQuery<User> query1 = em.createQuery("select u from User u where u.email = :email", User.class);
        query1.setParameter("email", user.getEmail());

        if (!query1.getResultList().isEmpty()) {
            throw new InvalidUserException("Person with such email already exists", 400);
        }
        if (!user.getPassword().equals(user.getPassword2())) {
            throw new InvalidUserException("Passwords do not match", 400);
        }

        int userAge = Utils.getUserAge(user.getBirth(), LocalDate.now());

        if (userAge >= 150 || userAge <= 8) {
            throw new InvalidUserException("Not supported age for user", 400);
        }
    }

    public void validateUserLogin(List<User> users) throws InvalidUserException {

        if (users.isEmpty()) {
            throw new InvalidUserException("Not such user", 400);
        }
    }

    public void validateImage(String image) throws InvalidUserException {

        pattern = Pattern.compile(IMAGE_PATTERN);
        matcher = pattern.matcher(image);
        if (!matcher.matches()) {
            throw new InvalidUserException("Allowed images formats: .jpg .png .jpeg", 400);
        }
    }


}
