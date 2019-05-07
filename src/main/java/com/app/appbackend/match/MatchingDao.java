package com.app.appbackend.match;

import com.app.appbackend.image.Image;
import com.app.appbackend.image.ImageDao;
import com.app.appbackend.message.Message;
import com.app.appbackend.user.User;
import com.app.appbackend.user.UserDto;
import com.app.appbackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Transactional
@Repository
public class MatchingDao {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    ImageDao imageDao;


    @Autowired
    Environment environment;


    List<UserDto> getMatches(String email) throws IOException {

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        User client = query.getResultList().get(0);
        Integer id = client.getId().intValue();
        List<UserDto> userDto = new LinkedList<>();

        TypedQuery<Matching> query1 = em.createQuery("SELECT m FROM Matching m WHERE m.fromUserId = :id AND m.likeValue = 1" +
                "AND m.toUserId IN (SELECT m.fromUserId from Matching m WHERE m.toUserId = :id AND m.likeValue = 1)", Matching.class);
        query1.setParameter("id", id);
        List<Matching> usersLikes = query1.getResultList();


        for (Matching matching : usersLikes) {

            //USER
            TypedQuery<User> query2 = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
            query2.setParameter("id", Long.valueOf(matching.toUserId));
            User user = query2.getSingleResult();

            //IMAGES
            List<Image> userImages = imageDao.getUserImages(user.getId());

            //LAST MESSAGE
            TypedQuery<Message> lastMessagequery = em.createQuery("SELECT m FROM Message m WHERE (m.fromUserId = :friendId " +
                    "AND m.toUserId = :userId) OR (m.fromUserId = :userId  AND m.toUserId = :friendId) ORDER BY m.dateSent", Message.class);
            lastMessagequery.setParameter("friendId", Long.valueOf(matching.toUserId));
            lastMessagequery.setParameter("userId", (long) id);


            List<Message> resultList1 = lastMessagequery.getResultList();
            Message lastMessage = resultList1.size() >= 1 ? resultList1.get(resultList1.size() - 1) : new Message(1L, 100L, 200L, "Say Hello to new friend!", false, LocalDateTime.now());


            if (id != 1 && !lastMessage.getMessageSeen()) {
                System.out.println("id = " + id);
                System.out.println("fromuserid = " + lastMessage.getFromUserId());
                System.out.println(lastMessage.getMessageSeen());


            }


            int age = Utils.getUserAge(user.getBirth(), LocalDate.now());

            userDto.add(new UserDto(user.getId(),
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
                    userImages,
                    new ArrayList<>(),
                    matching.seen,
                    lastMessage));

        }

        userDto.sort((o2, o1) -> o1.getLastMessage().getDateSent().compareTo(o2.getLastMessage().getDateSent()));

        return userDto;
    }

}