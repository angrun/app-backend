package com.app.appbackend.controllers;


import com.app.appbackend.dao.MessagesDao;
import com.app.appbackend.models.Message;
import com.app.appbackend.security.JwtDecoder;
import com.app.appbackend.views.MessageView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    MessagesDao messagesDao;

    private JwtDecoder decoder = new JwtDecoder();


    @ApiOperation("Gets the user with the specific id")
    @PostMapping()
    public void sendMessage(@RequestBody MessageView messageView) {
        messagesDao.sendMessage(messageView);
    }

    //OR AN OBJECT ?
    @GetMapping("/all/{friendId}")
    public List<Message> getMessages(@RequestHeader(value="Authorization") String authorization, @PathVariable Integer friendId) {
        return messagesDao.getMessages(decoder.getEmailFromToken(authorization), friendId);
    }


}
