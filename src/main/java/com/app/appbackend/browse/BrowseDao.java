package com.app.appbackend.browse;

import com.app.appbackend.hobby.Hobby;
import com.app.appbackend.image.Image;
import com.app.appbackend.match.Matching;
import com.app.appbackend.user.User;
import com.app.appbackend.utils.Utils;
import com.app.appbackend.filter.FilterDto;
import com.app.appbackend.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.app.appbackend.utils.Utils.DEFAULT_PIC;
import static com.app.appbackend.utils.Utils.SERVER_ADD;

@Repository
public class BrowseDao {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    Environment environment;


    List<UserDto> getAllUsers(FilterDto filterDto) {

        Integer userId = filterDto.getId();
        String city = filterDto.getCity();
        String country = filterDto.getCountry();
        String gender = filterDto.getGender();

        TypedQuery<User> usersQuery =
                em.createQuery("SELECT u FROM User u WHERE u.id NOT IN (SELECT m.toUserId from Matching m  WHERE m.fromUserId = :userId) " +
                        "AND u.id <> :userId AND u.city = :city AND u.country = :country AND u.gender = :gender", User.class);
        usersQuery.setParameter("userId", userId);
        usersQuery.setParameter("city", city);
        usersQuery.setParameter("country", country);
        usersQuery.setParameter("gender", gender);

        List<User> users = usersQuery.getResultList();

        List<UserDto> userDto = new LinkedList<>();

        for (User user : users) {

            //IMAGES
            TypedQuery<Image> images = em.createQuery("SELECT i FROM Image i WHERE i.userId = :userId ORDER BY i.dateCreated DESC", Image.class);
            images.setParameter("userId", user.getId());
            List<Image> resultList = images.getResultList();
            if (resultList.isEmpty()) {
                resultList.add(new Image(SERVER_ADD + ":" + environment.getProperty("server.port") + DEFAULT_PIC, user.getId(), LocalDateTime.now()));
            }

            //HOBBIES
            TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h WHERE h.userId = :userId", Hobby.class);
            query.setParameter("userId", (long) user.getId());
            List<Hobby> hobbies = query.getResultList();

            //AGE
            int age = Utils.getUserAge(user.getBirth(), LocalDate.now());

            userDto.add(new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getSurname(),
                    user.getEmail(),
                    user.getCity(),
                    user.getCountry(),
                    user.getGender(),
                    age,
                    user.getLikes(),
                    user.getBio(),
                    user.getRegisterDate(),
                    resultList,
                    hobbies,
                    false,
                    null));

        }

        return userDto;
    }

    @Transactional
    public void gradeUser(Matching matching) {

        em.persist(matching);
    }




}
