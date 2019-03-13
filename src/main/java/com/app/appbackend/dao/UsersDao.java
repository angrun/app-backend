package com.app.appbackend.dao;


import com.app.appbackend.models.Image;
import com.app.appbackend.models.User;
import com.app.appbackend.utils.Utils;
import com.app.appbackend.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class UsersDao {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    Environment environment;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    ImageDao imageDao;





    public UserView getUserById(Long id) throws IOException {

        TypedQuery<User> query = em.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);

        User user = query.getResultList().isEmpty() ? null : query.getSingleResult();
        assert user != null;

        int age = Utils.getUserAge(user.getBirth(), LocalDate.now());

        List<Image> userImages = imageDao.getUserImages(id);

        return new UserView(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getCity(), user.getCountry(), user.getGender(),
                age, user.getLikes(), user.getBio(), user.getRegisterDate(), userImages);
    }

    @Transactional
    @Modifying
    public User update(UserView userview) {

        User user = em.find(User.class, userview.getId());
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
