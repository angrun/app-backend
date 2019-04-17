package com.app.appbackend.match;

import com.app.appbackend.security.JwtDecoder;
import com.app.appbackend.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MatchingService {

    @Autowired
    MatchingDao matchingDao;

    private JwtDecoder decoder = new JwtDecoder();

    List<UserDto> getMatches(String authorization) {
        return matchingDao.getMatches(decoder.getEmailFromToken(authorization));
    }

    public Integer getUnseenMatches(String authorization) {
        return matchingDao.getUnseenMatches(decoder.getEmailFromToken(authorization));
    }
}
