package com.app.appbackend.hobby;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class HobbyDao {

    @PersistenceContext
    public EntityManager em;

    @Transactional
    public void addHobby(HobbyDto hobbyDto) {

        Hobby hobby = new Hobby();
        hobby.setName(hobbyDto.getHobby());
        hobby.setUserId(hobbyDto.getUserId());
        em.persist(hobby);
    }
    public List<Hobby> getHobbies(Integer userId) {

        TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h WHERE h.userId = :userId", Hobby.class);
        query.setParameter("userId", (long) userId);
        return query.getResultList();
    }


    @Transactional
    public void deleteHobby(HobbyDto hobbyDto) {
        System.out.println("here");
        Query query1 = em.createQuery("DELETE FROM Hobby h WHERE h.name = :name AND h.userId = :userId");
        query1.setParameter("name", hobbyDto.getHobby()).setParameter("userId", hobbyDto.getUserId()).executeUpdate();
    }
}
