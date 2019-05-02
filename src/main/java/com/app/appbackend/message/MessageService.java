package com.app.appbackend.message;


import com.app.appbackend.security.JwtDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class MessageService {

    @Autowired
    MessageDao messageDao;

    private JwtDecoder decoder = new JwtDecoder();

    @Transactional
    void sendMessage(MessageDto messageDto) {
        messageDao.sendMessage(messageDto);
    }

    List<Message> getMessages(String authorization, Integer friendId) {
        return messageDao.getMessages(decoder.getEmailFromToken(authorization), friendId);
    }

    void updateMessagesToBeSeen(String authorization, Integer friendId) {
        messageDao.updateMessagesToBeSeen(decoder.getEmailFromToken(authorization), friendId);
    }

}
