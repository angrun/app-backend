package com.app.appbackend.dao;

import com.app.appbackend.models.Image;
import com.app.appbackend.models.User;
import com.app.appbackend.utils.Utils;
import com.app.appbackend.views.UserView;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BrowseDao {

    @PersistenceContext
    public EntityManager em;

    public List<User> getFilteredUsers(String city, String country, String gender) {

        TypedQuery<User> query1 = em.createQuery("select u from User u where " +
                "u.city = :city and u.country = :country and u.gender = :gender", User.class);
        query1.setParameter("city", city);
        query1.setParameter("country", country);
        query1.setParameter("gender", gender);

        return query1.getResultList();
    }

    public List<UserView> getAllUsers() {

        List<User> users = em.createQuery("select u from User u", User.class).getResultList();

        List<UserView> userViews = new LinkedList<>();

        for (User user : users) {

            int age = Utils.getUserAge(user.getBirth(), LocalDate.now());

            userViews.add(new UserView(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getCity(), user.getCountry(), user.getGender(),
                    age, user.getLikes(), user.getBio(), user.getRegisterDate(), Arrays.asList(new Image("http://localhost:8081/anonym.png/", user.getId(), LocalDateTime.now()))));

        }

        return userViews;
    }

}
