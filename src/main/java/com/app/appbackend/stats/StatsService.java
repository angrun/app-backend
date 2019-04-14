package com.app.appbackend.stats;

import com.app.appbackend.user.User;
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
        List<Object[]> resultList = statsDao.getUsersByCountry();

        HashMap<String, Integer> country = new HashMap<>();

        for (Object[] aResultList : resultList) {
            country.put(aResultList[1].toString(),
                    Integer.valueOf(aResultList[0].toString()));
        }
        return country;
    }


}
