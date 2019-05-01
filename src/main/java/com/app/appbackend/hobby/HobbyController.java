package com.app.appbackend.hobby;


import com.app.appbackend.exceptions.InvalidUserException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/hobby")
public class HobbyController {

    @Autowired
    HobbyService hobbyService;

    @ApiOperation("Adds hobby to user")
    @PostMapping()
    public void addHobby(@RequestBody HobbyDto hobbyDto) throws InvalidUserException {
        hobbyService.addHobby(hobbyDto);
    }

    @GetMapping("/{userId}")
    public List<Hobby> getMessages(@PathVariable Integer userId) {
        return hobbyService.getHobbies(userId);
    }

    @Transactional
    @DeleteMapping
    public void deleteHobby(@RequestBody HobbyDto hobbyDto) {
        hobbyService.deleteHobby(hobbyDto);
    }



}
