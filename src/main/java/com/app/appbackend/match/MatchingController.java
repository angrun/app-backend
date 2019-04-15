package com.app.appbackend.match;

import com.app.appbackend.user.UserDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    @GetMapping("/all")
    @ApiOperation("Gets all users")
    public List<UserDto> getAllUsers(@RequestHeader(value = "Authorization") String authorization) {
        return matchingService.getMatches(authorization);
    }
}