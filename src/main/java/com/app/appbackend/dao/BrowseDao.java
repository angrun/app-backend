package com.app.appbackend.dao;

import com.app.appbackend.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public List<User> getAllUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

}
