package com.app.appbackend.services;

import com.app.appbackend.dao.UserAuthorizationDao;
import com.app.appbackend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserAuthorizationDao usersDao;

    @Override
    public UserDetails loadUserByUsername(String email) {
        System.out.println("Email is:" + email);
        List<User> users = usersDao.findByEmail(email);
        if(email == null || email.equals("") || users.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = users.get(0);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getAuthorities());
    }

}
