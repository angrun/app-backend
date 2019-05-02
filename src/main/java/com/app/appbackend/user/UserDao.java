package com.app.appbackend.user;


import com.app.appbackend.image.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class UserDao {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    Environment environment;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    ImageDao imageDao;


    User getUserByEmail(String email) {

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);

        assert !query.getResultList().isEmpty();
        User user = query.getResultList().isEmpty() ? null : query.getSingleResult();
        assert user != null;
        return user;
    }

    @Transactional
    @Modifying
    public User update(UserDto useDto) {

        User user = em.find(User.class, useDto.getId());
        user.setName(useDto.getName());
        user.setSurname(useDto.getSurname());
        user.setGender(useDto.getGender());
        user.setCity(useDto.getCity());
        user.setCountry(useDto.getCountry());
        user.setBio(useDto.getBio());
        user.setLikes(useDto.getLikes());

        em.persist(user);

        return user;
    }

}
