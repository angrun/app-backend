package com.app.appbackend.registration;

import com.app.appbackend.user.User;
import com.app.appbackend.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    Validation validation;

    @Transactional
    public User register(User user) {

        user.setLikes(0);
        user.setRegisterDate(LocalDate.now());
        em.persist(user);
        return user;
    }

    public List<User> findByEmail(String email) {

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getResultList();
    }

}
