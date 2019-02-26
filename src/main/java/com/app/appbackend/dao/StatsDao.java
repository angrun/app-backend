package com.app.appbackend.dao;

import com.app.appbackend.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class StatsDao {

    @PersistenceContext
    public EntityManager em;


    public Integer getGenderEquality() {

        TypedQuery<Long> query1 = em.createQuery("select COUNT(u) from User u where u.gender = :gender", Long.class);
        query1.setParameter("gender", "FEMALE");
        System.out.println(query1.getSingleResult());

        return 0;
    }
}
