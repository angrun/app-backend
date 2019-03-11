package com.app.appbackend.dao;

import com.app.appbackend.models.User;
import com.app.appbackend.validation.NotFoundException;
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
    public User register(User user) {

        user.setLikes(0);
        user.setRegisterDate(LocalDate.now());

        em.persist(user);
        return user;
    }


    public List<User> logIn(UserLoginView userLoginView) throws NotFoundException {

        TypedQuery<User> query = em.createQuery("select u from User u where u.email = :email AND u.password = :password", User.class);
        query.setParameter("email", userLoginView.email);
        query.setParameter("password", userLoginView.password);

        System.out.println("LIST " + query.getResultList());

        return query.getResultList();

    }

}
