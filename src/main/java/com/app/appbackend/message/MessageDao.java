package com.app.appbackend.message;

import com.app.appbackend.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class MessageDao {

    @PersistenceContext
    public EntityManager em;


    @Transactional
    void sendMessage(MessageDto messageDto)
    {
        Message message = new Message();

        message.setFromUserId(messageDto.getFromUserId());
        message.setToUserId(messageDto.getToUserId());
        message.setMessage(messageDto.getMessage());
        message.setDateSent(LocalDateTime.now());

        em.persist(message);
    }

    List<Message> getMessages(String email, Integer friendId) {

        TypedQuery<User> query1 = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query1.setParameter("email", email);
        User client = query1.getResultList().get(0);

        int userId = client.getId().intValue();

        System.out.println("--------------------------");
        System.out.println(userId);
        System.out.println(friendId);
        System.out.println("--------------------------");


        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m WHERE (m.fromUserId = :friendId " +
                "AND m.toUserId = :userId) OR (m.fromUserId = :userId  AND m.toUserId = :friendId) ORDER BY m.dateSent", Message.class);
        query.setParameter("friendId", Long.valueOf(friendId));
        query.setParameter("userId", (long) userId);
        System.out.println(query.getResultList());


        Query query2 = em.createQuery("UPDATE Matching m SET m.seen = TRUE WHERE m.fromUserId = :userId and m.toUserId = :friendId");
        query2.setParameter("friendId", Long.valueOf(friendId));
        query2.setParameter("userId", (long) userId);
        query2.executeUpdate();


        return query.getResultList();

    }
}
