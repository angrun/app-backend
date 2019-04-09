package com.app.appbackend.dao;

import com.app.appbackend.models.Message;
import com.app.appbackend.models.User;
import com.app.appbackend.views.MessageView;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MessagesDao {

    @PersistenceContext
    public EntityManager em;


    @Transactional
    public void sendMessage(MessageView messageView)
    {
        Message message = new Message();

        message.setFromUserId(messageView.getFromUserId());
        message.setToUserId(messageView.getToUserId());
        message.setMessage(messageView.getMessage());
        message.setDateSent(LocalDateTime.now());

        em.persist(message);
    }

    public List<Message> getMessages(String email, Integer friendId) {

        TypedQuery<User> query1 = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query1.setParameter("email", email);
        User client = query1.getResultList().get(0);

        Integer userId = client.getId().intValue();

        System.out.println("--------------------------");
        System.out.println(userId);
        System.out.println(friendId);
        System.out.println("--------------------------");


        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m where m.fromUserId = :userId " +
                "AND m.toUserId = :friendId ORDER BY m.dateSent", Message.class);
        query.setParameter("friendId", Long.valueOf(friendId));
        query.setParameter("userId", Long.valueOf(userId));

        System.out.println(query.getResultList());

        return query.getResultList();

    }
}
