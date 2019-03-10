package com.app.appbackend.dao;

import com.app.appbackend.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;


@Repository
public class UserRegistrationDao {

    @PersistenceContext
    public EntityManager em;

    @Transactional
    public User register(User user) {

        user.setLikes(0);
        user.setRegisterDate(LocalDate.now());

        em.persist(user);
        return user;
    }

}
