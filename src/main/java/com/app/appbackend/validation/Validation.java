package com.app.appbackend.validation;

import com.app.appbackend.exceptions.InvalidUserException;
import com.app.appbackend.hobby.Hobby;
import com.app.appbackend.hobby.HobbyDto;
import com.app.appbackend.user.User;
import com.app.appbackend.utils.Utils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.app.appbackend.utils.Utils.BAD_REQUEST;

@Component
public class Validation {

    @PersistenceContext
    public EntityManager em;

    private Pattern pattern;
    private Matcher matcher;

    private static final String IMAGE_PATTERN =
            "([^\\s]+(\\.(?i)(jpg|png|jpeg))$)";

    public void validateUserRegistration(User user) throws InvalidUserException {

        emailExists(user.getEmail(), user.getId());


        if (!user.getPassword().equals(user.getPassword2())) {
            throw new InvalidUserException("Passwords do not match", BAD_REQUEST);
        }

        int userAge = Utils.getUserAge(user.getBirth(), LocalDate.now());

        if (userAge >= 150 || userAge <= 8) {
            throw new InvalidUserException("Not supported age for user", BAD_REQUEST);
        }
    }

    public void emailExists (String email, Long id) throws InvalidUserException {
        TypedQuery<User> query1 = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query1.setParameter("email", email);

        if (!query1.getResultList().isEmpty()) {
            throw new InvalidUserException("Person with such email already exists", BAD_REQUEST);
        }
    }


    public void validateImage(String image) throws InvalidUserException {

        pattern = Pattern.compile(IMAGE_PATTERN);
        matcher = pattern.matcher(image);
        if (!matcher.matches()) {
            throw new InvalidUserException("Allowed images formats: .jpg .png .jpeg", BAD_REQUEST);
        }
    }

    public void validateHobby(HobbyDto hobbyDto) throws InvalidUserException {


        TypedQuery<Hobby> query1 = em.createQuery("SELECT h FROM Hobby h WHERE h.userId = :userId AND h.name = :name", Hobby.class);
        query1.setParameter("userId", hobbyDto.getUserId());
        query1.setParameter("name", hobbyDto.getHobby());


        if (!query1.getResultList().isEmpty()) {
            throw new InvalidUserException("You have already declared such hobby", BAD_REQUEST);
        }
    }


}
