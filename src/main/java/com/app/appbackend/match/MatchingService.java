package com.app.appbackend.match;

import com.app.appbackend.security.JwtDecoder;
import com.app.appbackend.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
public class MatchingService {

    @Autowired
    MatchingDao matchingDao;

    private JwtDecoder decoder = new JwtDecoder();

    List<UserDto> getMatches(String authorization) throws IOException {

        List<UserDto> matches = matchingDao.getMatches(decoder.getEmailFromToken(authorization));
        matches.sort((o2, o1) -> o1.getLastMessage().getDateSent().compareTo(o2.getLastMessage().getDateSent()));
        return matches;
    }

}
