package com.app.appbackend.hobby;


import com.app.appbackend.exceptions.InvalidUserException;
import com.app.appbackend.security.JwtDecoder;
import com.app.appbackend.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HobbyService {

    @Autowired
    HobbyDao hobbyDao;

    @Autowired
    Validation validation;

    private JwtDecoder decoder = new JwtDecoder();


    void addHobby(HobbyDto hobbyDto) throws InvalidUserException {
        validation.validateHobby(hobbyDto);
        hobbyDao.addHobby(hobbyDto);


    }

//    List<Hobby> getHobbies(String email) {
//        return hobbyDao.getHobbies(decoder.getEmailFromToken(email));
//    }

    List<Hobby> getHobbies(Integer userId) {
        return hobbyDao.getHobbies(userId);
    }

    @Transactional
    void deleteHobby(HobbyDto hobbyDto) {
        hobbyDao.deleteHobby(hobbyDto);
    }
}
