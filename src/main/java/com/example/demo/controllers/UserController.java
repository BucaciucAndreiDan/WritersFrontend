package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all/{page}")
    public ResponseEntity<List<User>> findAllUsers(@PathVariable int page) {
        List<User> users = userService.findAllUsers(page);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/findUser/{cnp}")
    public ResponseEntity<User> findUser(@PathVariable long cnp) {
        User employee = userService.findUserByCnp(cnp);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(value = {"/findUsers/{cnp}/{name}/{page}", "/findUsers//{name}/{page}", "/findUsers/{cnp}//{page}", "/findUsers///{page}"})
    public ResponseEntity<List<User>> findUsers(@PathVariable(required = false) Long cnp, @PathVariable(required = false) String name, @PathVariable int page) {
        if ((cnp == null) && (name == null))
            return findAllUsers(page); //show all
        else {
            List<User> users = userService.findUsers(cnp, name, page);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
}