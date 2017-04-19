package com.panos.mir.controllers;

import com.panos.mir.exceptions.BadRequestException;
import com.panos.mir.repositories.UserRepository;
import com.panos.mir.exceptions.NotFoundException;
import com.panos.mir.model.Users;
import com.panos.mir.rootnames.CustomJsonRootName;
import com.panos.mir.rootnames.ApiRootElementNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Panos on 3/30/2017.
 */
@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    //Returns all the users (For testing purposes only)
    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Users>>> getUsers() {
        List<Users> users = (List<Users>) repository.findAll();
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).users(), users);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Register a user
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> create(@RequestBody Users user) {
        if (repository.findFirstByUsernameOrPassword(user.getUsername(), user.getPassword()) != null) {
            Users saved = repository.save(user);
            return new ResponseEntity<Users>(saved, HttpStatus.CREATED);
        }
        else {
            throw new BadRequestException();
        }
    }

    //Login call.
    @PostMapping(path = "/all/findUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> findUser(@RequestBody Users user) {
        if (repository.findFirstByUsernameAndPassword(user.getUsername(), user.getPassword()) != null) {
            return new ResponseEntity<Users>(repository.findFirstByUsernameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
        } else {
            throw new NotFoundException();
        }
    }

}
