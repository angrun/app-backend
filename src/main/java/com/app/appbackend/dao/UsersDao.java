package com.app.appbackend.dao;


import com.app.appbackend.models.User;
import com.app.appbackend.utils.Utils;
import com.app.appbackend.views.UserView;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
public class UsersDao {

    @PersistenceContext
    public EntityManager em;


    public UserView getUserById(Long id) {

        TypedQuery<User> query = em.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);

        User user = query.getResultList().isEmpty() ? null : query.getSingleResult();
        assert user != null;

        int age = Utils.getUserAge(user.getBirth(), LocalDate.now());

        System.out.println(user.getRegisterDate());
        System.out.println(user.getBirth());

        return new UserView(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getCity(), user.getCountry(), user.getGender(),
               age, user.getLikes(), user.getBio(), user.getRegisterDate());

    }

    @Transactional
    @Modifying
    public User update(UserView userview) {

        User user = em.find(User.class,userview.getId());
        user.setName(userview.getName());
        user.setSurname(userview.getSurname());
        user.setGender(userview.getGender());
        user.setCity(userview.getCity());
        user.setCountry(userview.getCountry());
        user.setBio(userview.getBio());
        user.setLikes(userview.getLikes());

        return user;
    }

//    @Transactional
//    public void delete(Long userId) {
//
//        User user = em.find(User.class, userId);
//
//        if (user != null) {
//            em.remove(user);
//        }
//    }
//
//
//    @Transactional
//    public void delete() {
//
//        Query query = em.createQuery("delete from User");
//        query.executeUpdate();
//    }


}
