package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Controller;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Role;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.User;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user){
        user.setRole(Role.NONE);
        return new ResponseEntity<>(userService.createUser(user) , HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        return new ResponseEntity<>( userService.delete(id), HttpStatus.ACCEPTED);
    }
}
