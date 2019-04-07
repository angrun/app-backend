package com.app.appbackend.dao;

import com.app.appbackend.models.Message;
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

    public List<Message> getMessages(Integer userId, Integer friendId) {

        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m where m.fromUserId = :friendId " +
                "AND m.toUserId = :userId ORDER BY m.dateSent", Message.class);
        query.setParameter("friendId", Long.valueOf(friendId));
        query.setParameter("userId", Long.valueOf(userId));

        return query.getResultList();

    }
}
