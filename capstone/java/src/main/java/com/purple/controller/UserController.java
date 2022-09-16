package com.purple.controller;


import com.purple.dao.UserDao;
import com.purple.handler.UserHandlerable;
import com.purple.model.User;
import com.purple.model.UserSearchCriteria;
import com.purple.security.AuthAspect;
import com.purple.security.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController extends BaseController {

    private final UserHandlerable userHandler;

    @Autowired
    public UserController(UserHandlerable userHandler) {
        this.userHandler = userHandler;
    }

    /*
        JSON example
        {
        "userName":"TestUsername",
        "password":"testpassword"
        }
     */
    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletResponse response) {
        if (user == null) {
            return ResponseEntity.badRequest().body("username or password is incorrect");
        }
        User returnUser = this.userHandler.login(user.getUserName(), user.getPassword());
        if (returnUser == null) {
            return ResponseEntity.badRequest().body("username or password is incorrect");
        }
        AuthAspect.setUserCookie(returnUser, response);
        return ResponseEntity.ok(returnUser);
    }

    @GetMapping(value = "/user/current",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCurrentOrBlankUser(HttpServletRequest request) {
        User currentUser = super.getCurrentUserFromCookie(request);
        if (currentUser != null) {
            return ResponseEntity.ok(currentUser);
        }
        return ResponseEntity.ok(new User());
    }

    @PutMapping(value = "/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Authorized
    public ResponseEntity<?> updateUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        if (user == null) {
            return ResponseEntity.badRequest().body("User is empty");
        }
        User currentUser = super.getCurrentUserFromAttribute(request);

        User newlyAddedUser = this.userHandler.saveUser(currentUser, user, super.getErrorMessages());
        if (newlyAddedUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newlyAddedUser);
        }
        return ResponseEntity.badRequest().body(super.getErrorString());
    }


    @PostMapping(value = "/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {

        User newlyAddedUser = this.userHandler.registerUser(null, user, super.getErrorMessages());
        if (newlyAddedUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newlyAddedUser);
        }
        return super.sendErrors("Couldn't register user");
    }

    @GetMapping(value = "/user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Authorized
    public ResponseEntity<User> getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable long id) {
        User currentUser = super.getCurrentUserFromAttribute(request);

        User searchedUser = this.userHandler.getUserById(currentUser, id);
        if (searchedUser != null) {
            return ResponseEntity.ok(searchedUser);
        }

        return ResponseEntity.status(403).build();
    }

    @PostMapping(value = "/userSearch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Authorized(requiredRole = "Admin")
    public ResponseEntity<?> getUsers(HttpServletRequest request, HttpServletResponse response, @RequestBody UserSearchCriteria search) {
        User currentUser = super.getCurrentUserFromAttribute(request);

        List<User> searchedUsers = this.userHandler.getUsers(currentUser, search);
        if (searchedUsers != null) {
            return ResponseEntity.ok(searchedUsers);
        }
        return ResponseEntity.badRequest().body(super.getErrorString());
    }

    @GetMapping(value = "/userLogout",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logoutUser(HttpServletRequest request, HttpServletResponse response) {
        AuthAspect.removeUserFromCookie(request, response);
        return ResponseEntity.ok(new User());
    }

}
