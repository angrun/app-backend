package com.app.appbackend.dao;

import com.app.appbackend.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;


@Repository
public class  StatsDao {

    @PersistenceContext
    public EntityManager em;


    public List<Long> getGenderEquality() {

        TypedQuery<Long> query1 = em.createQuery("select COUNT(u) from User u where u.gender = :gender", Long.class);
        TypedQuery<Long> query2 = em.createQuery("select COUNT(u) from User u where u.gender = :gender", Long.class);
        query1.setParameter("gender", "FEMALE");
        query2.setParameter("gender", "MALE");
        Long woman = query1.getSingleResult();
        Long men = query2.getSingleResult();

        return Arrays.asList(woman, men);
    }

    public List<User> getUsersWithGreatestLikes() {

        TypedQuery<User> query = em.createQuery("Select u from User u order by u.likes desc", User.class);
        return query.setMaxResults(10).getResultList();
    }
}
