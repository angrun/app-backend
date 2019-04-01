package com.app.appbackend.dao;


import com.app.appbackend.models.User;
import com.app.appbackend.views.UserLoginView;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Repository
public class UserAuthorizationDao {

    @PersistenceContext
    public EntityManager em;

    @Transactional
    public User register(User user)  {

        user.setLikes(0);
        user.setRegisterDate(LocalDate.now());


        em.persist(user);
        return user;
    }


    public List<User> logIn(UserLoginView userLoginView) {

        TypedQuery<User> query = em.createQuery("select u from User u where u.email = :email AND u.password = :password", User.class);
        query.setParameter("email", userLoginView.username);
        query.setParameter("password", userLoginView.password);

        return query.getResultList();

    }

    public List<User> findByEmail(String email) {

        TypedQuery<User> query = em.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", email);

        return query.getResultList();
    }

}
