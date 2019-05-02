package com.app.appbackend.user;

import com.app.appbackend.exceptions.InvalidUserException;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Base64;


@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userServiceImpl;

    @Autowired
    UserService userService;


    @ApiOperation("Gets the user with the specific id")
    @GetMapping(produces = "application/json")
    public UserDto getUser(@RequestHeader(value = "Authorization") String authorization) {
        System.out.println(userServiceImpl.getUserByEmail(authorization));
        return userServiceImpl.getUserByEmail(authorization);
    }

    @ApiOperation("Updates user information")
    @PutMapping()
    public User updateUser(@RequestBody @Valid UserDto userDto) throws InvalidUserException {
        return userService.updateUser(userDto);
    }

    @ApiOperation("Updates user information")


    @PostMapping("images")
    public void createFile(@RequestParam("file") MultipartFile file,
                           @RequestHeader(value = "Authorization") String authorization) throws InvalidUserException {
        userService.createFile(file, authorization);

    }

    @ApiOperation("Updates user information")
    @GetMapping("images")
    public Resource createFile() {
        return userService.createFile();
    }

    @GetMapping(
            value = "/imagesTest"
//            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public  String getImage() throws IOException {

        byte[] fileContent = FileUtils.readFileToByteArray(new File("../images/download.jpeg"));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        System.out.println(encodedString);
        return encodedString;
    }
}
