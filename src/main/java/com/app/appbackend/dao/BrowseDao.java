package com.app.appbackend.dao;

import com.app.appbackend.models.Image;
import com.app.appbackend.models.User;
import com.app.appbackend.utils.Utils;
import com.app.appbackend.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BrowseDao {

    private static final String SERVER_ADD = "http://" + InetAddress.getLoopbackAddress().getHostName();
    private static final String DEFAULT_PIC = "/anonym.png";

    @PersistenceContext
    public EntityManager em;

    @Autowired
    Environment environment;



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

            TypedQuery<Image> images = em.createQuery("select i from Image i WHERE i.userId = :userId order by i.dateCreated DESC", Image.class);
            images.setParameter("userId", user.getId());

            List<Image> resultList = images.getResultList();

            if (resultList.isEmpty()) {
                resultList.add(new Image(SERVER_ADD + ":" + environment.getProperty("server.port") + DEFAULT_PIC, user.getId(), LocalDateTime.now()));
            }

            int age = Utils.getUserAge(user.getBirth(), LocalDate.now());

            userViews.add(new UserView(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getCity(), user.getCountry(), user.getGender(),
                    age, user.getLikes(), user.getBio(), user.getRegisterDate(), resultList));

        }

        return userViews;
    }

}
