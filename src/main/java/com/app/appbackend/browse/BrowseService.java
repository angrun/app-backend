package com.app.appbackend.browse;


import com.app.appbackend.user.UserDao;
import com.app.appbackend.match.Matching;
import com.app.appbackend.filter.FilterDto;
import com.app.appbackend.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BrowseService {

    @Autowired
    BrowseDao browseDao;

    @Autowired
    UserDao usersDao;

    List<UserDto> getAllUsers(FilterDto filterDto) throws IOException {
        return browseDao.getAllUsers(filterDto);
    }

    void gradeUser(Matching matching) {
        browseDao.gradeUser(matching);
    }

}
