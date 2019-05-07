package com.app.appbackend.stats;

import com.app.appbackend.hobby.HobbyAmountDto;
import com.app.appbackend.user.User;
import com.app.appbackend.user.UserAmountMessagesDto;
import com.app.appbackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StatsService {

    @Autowired
    StatsDao statsDao;

    List<Long> getGenderProportion() {
        return statsDao.getGenderEquality();
    }

    List<User> getUsersWithGreatestLikes() {
        return statsDao.getUsersWithGreatestLikes();
    }

    double getUsersMatchingPercentage(Integer userId) {
        return statsDao.getMatchPercentage(userId);
    }

    HashMap<String, Integer> getUsersByCountry() {
        return Utils.getStringIntegerHashMap(statsDao.getUsersByCountry());
    }

    List<HobbyAmountDto> getUsersByHobby() {

        List<Object[]> usersByHobby = statsDao.getUsersByHobby();
        List<HobbyAmountDto> res = new ArrayList<>();

        for (Object[] row : usersByHobby) {
            HobbyAmountDto hobbyAmountDto = new HobbyAmountDto();
            hobbyAmountDto.setHobbyAmount((Long) row[0]);
            hobbyAmountDto.setHobbyName((String) row[1]);
            res.add(hobbyAmountDto);
        }
        return res;
    }



    List<UserAmountMessagesDto> getUsersByMessages() {

        List<Object[]> usersByMessages = statsDao.getUsersByMessages();
        List<UserAmountMessagesDto> res = new ArrayList<>();

        for (Object[] row : usersByMessages) {
            UserAmountMessagesDto userAmountMessagesDto = new UserAmountMessagesDto();
            userAmountMessagesDto.setAmountOfMessages((Long) row[0]);
            userAmountMessagesDto.setName((String) row[1]);
            res.add(userAmountMessagesDto);
        }

        return res;
    }
}
