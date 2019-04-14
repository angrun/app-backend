package com.app.appbackend.match;

import com.app.appbackend.image.Image;
import com.app.appbackend.user.User;
import com.app.appbackend.utils.Utils;
import com.app.appbackend.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static com.app.appbackend.utils.Utils.DEFAULT_PIC;
import static com.app.appbackend.utils.Utils.SERVER_ADD;


@Service
public class MatchingDao {


    @PersistenceContext
    public EntityManager em;


    @Autowired
    Environment environment;


    List<UserDto> getMatches(String email) {

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        User client = query.getResultList().get(0);

        Integer id = client.getId().intValue();

        List<UserDto> userDto = new LinkedList<>();

        TypedQuery<Integer> query1 = em.createQuery("SELECT m.toUserId FROM Matching m WHERE m.fromUserId = :id AND m.likeValue = 1" +
                "AND m.toUserId IN (SELECT m.fromUserId from Matching m WHERE m.toUserId = :id AND m.likeValue = 1)", Integer.class);
        query1.setParameter("id", id);
        List<Integer> usersLikes = query1.getResultList();


        for (Integer usersLike : usersLikes) {

            TypedQuery<User> query2 = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
            query2.setParameter("id", Long.valueOf(usersLike));
            User user = query2.getSingleResult();

            TypedQuery<Image> images = em.createQuery("SELECT i FROM Image i WHERE i.userId = :id ORDER BY i.dateCreated DESC", Image.class);
            images.setParameter("id", Long.valueOf(usersLike));

            List<Image> resultList = images.getResultList();

            if (resultList.isEmpty()) {
                resultList.add(new Image(SERVER_ADD + ":" + environment.getProperty("server.port") + DEFAULT_PIC, user.getId(), LocalDateTime.now()));
            }

            int age = Utils.getUserAge(user.getBirth(), LocalDate.now());

            userDto.add(new UserDto(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getCity(), user.getCountry(), user.getGender(),
                    age, user.getLikes(), user.getBio(), user.getRegisterDate(), resultList));

        }

        return userDto;

    }


}
