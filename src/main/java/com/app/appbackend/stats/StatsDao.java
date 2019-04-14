package com.app.appbackend.stats;

import com.app.appbackend.user.User;;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;


@Repository
public class StatsDao {

    @PersistenceContext
    public EntityManager em;


    List<Long> getGenderEquality() {

        TypedQuery<Long> query1 = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.gender = :gender", Long.class);
        TypedQuery<Long> query2 = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.gender = :gender", Long.class);
        query1.setParameter("gender", "FEMALE");
        query2.setParameter("gender", "MALE");
        Long woman = query1.getSingleResult();
        Long men = query2.getSingleResult();

        return Arrays.asList(woman, men);
    }

    List<User> getUsersWithGreatestLikes() {

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u ORDER BY u.likes DESC", User.class);
        return query.setMaxResults(10).getResultList();
    }


    double getMatchPercentage(Integer id) {


        TypedQuery<Integer> query1 = em.createQuery("SELECT m.toUserId FROM Matching m WHERE m.fromUserId = :id AND m.likeValue = 1" +
                "AND m.toUserId IN (SELECT m.fromUserId FROM Matching m WHERE m.toUserId = :id AND m.likeValue = 1)", Integer.class);
        query1.setParameter("id", id);

        int totalMatches = query1.getResultList().size();

        TypedQuery<Integer> query2 = em.createQuery("SELECT m.toUserId FROM Matching m WHERE m.fromUserId = :id AND m.likeValue = 1", Integer.class);
        query2.setParameter("id", id);

        int totalLikes = query2.getResultList().size();
        return Math.round((double) totalMatches / totalLikes * 100);

    }


    List<Object[]> getUsersByCountry() {

        TypedQuery<Object[]> query1 = em.createQuery("SELECT COUNT(u), u.country FROM User u GROUP BY u.country", Object[].class);
        return query1.getResultList();




    }

}
