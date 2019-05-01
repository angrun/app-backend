package com.app.appbackend.match;

import com.app.appbackend.user.UserDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/match")
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    @GetMapping("/all")
    @ApiOperation("Gets all users")
    public List<UserDto> getAllUsers(@RequestHeader(value = "Authorization") String authorization) {
        return matchingService.getMatches(authorization);
    }

//
//    //Return unseen or only count
//    @GetMapping("/unseen")
//    @ApiOperation("Get unseen users mathces")
//    public Integer getUnseenUsers(String authorization) {
//        return matchingService.getUnseenMatches(authorization);
//    }
//
//
//    @PostMapping("/doSeen")
//    @ApiOperation("Make users seen")
//    public void makeUsersSeen(@RequestBody List<Integer> ids) {
//        matchingService.makeMessagesSeen(ids);
//    }

}
