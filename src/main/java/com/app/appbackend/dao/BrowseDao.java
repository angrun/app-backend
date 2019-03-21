package com.app.appbackend.dao;

import com.app.appbackend.models.Image;
import com.app.appbackend.models.Matching;
import com.app.appbackend.models.User;
import com.app.appbackend.utils.Utils;
import com.app.appbackend.views.FilterView;
import com.app.appbackend.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static com.app.appbackend.utils.Utils.DEFAULT_PIC;
import static com.app.appbackend.utils.Utils.SERVER_ADD;

@Repository
public class BrowseDao {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    Environment environment;


//    public List<User> getFilteredUsers(String city, String country, String gender) {
//
//        TypedQuery<User> query1 = em.createQuery("SELECT u FROM User u WHERE " +
//                "u.city = :city AND u.country = :country AND u.gender = :gender", User.class);
//        query1.setParameter("city", city);
//        query1.setParameter("country", country);
//        query1.setParameter("gender", gender);
//
//        return query1.getResultList();
//    }

    public List<UserView> getAllUsers(FilterView filterView) {


        Long userId = filterView.getId();

        TypedQuery<User> usersQuery =
                em.createQuery("SELECT u FROM User u WHERE NOT EXISTS (SELECT m from Matching m  WHERE m.toUserId = :userId) " +
                        "AND u.id <> :userId AND u.city = :city AND u.country = :country AND u.gender = :gender", User.class);
        usersQuery.setParameter("userId", userId);

        List<User> users = usersQuery.getResultList();

        List<UserView> userViews = new LinkedList<>();

        for (User user : users) {

            TypedQuery<Image> images = em.createQuery("SELECT i FROM Image i WHERE i.userId = :userId ORDER BY i.dateCreated DESC", Image.class);
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

    @Transactional
    public void gradeUser(Matching matching) {

        em.persist(matching);
    }




}
