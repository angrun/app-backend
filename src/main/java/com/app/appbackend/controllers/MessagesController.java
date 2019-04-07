package com.app.appbackend.controllers;


import com.app.appbackend.dao.MessagesDao;
import com.app.appbackend.models.Message;
import com.app.appbackend.views.MessageView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    MessagesDao messagesDao;


    @ApiOperation("Gets the user with the specific id")
    @PostMapping()
    public void sendMessage(@RequestBody MessageView messageView) {
        messagesDao.sendMessage(messageView);
    }

    //OR AN OBJECT ?
    @GetMapping("/{friendId}")
    public List<Message> getMessages(Integer userId, @PathVariable Integer friendId) {
        return messagesDao.getMessages(userId, friendId);
    }


}
