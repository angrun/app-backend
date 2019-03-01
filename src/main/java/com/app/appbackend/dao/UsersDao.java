package com.app.appbackend.dao;


import com.app.appbackend.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UsersDao {

    @PersistenceContext
    public EntityManager em;


    public User getUserById(Long id) {

        TypedQuery<User> query = em.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getResultList().isEmpty() ? null : query.getSingleResult();

    }

    @Transactional
    public User update(User user) {

        em.merge(user);
        return user;
    }

    @Transactional
    public void delete(Long userId) {

        User user = em.find(User.class, userId);

        if (user != null) {
            em.remove(user);
        }
    }


    @Transactional
    public void delete() {

        Query query = em.createQuery("delete from User");
        query.executeUpdate();
    }


}
