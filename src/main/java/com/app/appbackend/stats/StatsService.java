package com.app.appbackend.stats;

import com.app.appbackend.user.User;
import com.app.appbackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    HashMap<String, Integer> getUsersByHobby() {
        return Utils.getStringIntegerHashMap(statsDao.getUsersByHobby());
    }

    HashMap<String, Integer> getUsersByMessages() {
        return Utils.getStringIntegerHashMap(statsDao.getUsersByMessages());
    }
}
