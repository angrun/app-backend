package com.app.appbackend.match;

import com.app.appbackend.user.UserDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/match")
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    @GetMapping("/all")
    @ApiOperation("Gets all users")
    public List<UserDto> getAllUsers(@RequestHeader(value = "Authorization") String authorization) throws IOException {
        return matchingService.getMatches(authorization);
    }

}
