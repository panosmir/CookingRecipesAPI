package com.panos.mir.controllers;

import com.panos.mir.exceptions.BadRequestException;
import com.panos.mir.exceptions.NotFoundException;
import com.panos.mir.model.Recipes;
import com.panos.mir.model.Users;
import com.panos.mir.repositories.RecipesRepository;
import com.panos.mir.repositories.UserRepository;
import com.panos.mir.rootnames.ApiRootElementNames;
import com.panos.mir.rootnames.CustomJsonRootName;
import com.panos.mir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RecipesRepository mRecipesRepository;

    @Autowired
    private UserService userService;

    //Register a user
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> create(@RequestBody Users user) {
        return userService.createUser(user);
    }

    //Login call.
    //// TODO: 4/21/2017 Create a logout POST call. Reqs are a boolean value in database. Grab that value if true so recipes
    @PostMapping(path = "/all/findUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> findUser(@RequestBody Users user) {
        return userService.findUser(user);
    }

    //When favorite system will be implemented it'll be moved to UserService.
    @GetMapping("/userFavorites/{id}")
    public ResponseEntity<Map<String, Iterable<Recipes>>> getUserFavorites(@PathVariable("id") int id) {
        Set<Recipes> favRecipes = repository.getUserFavorites(id);
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), favRecipes);
        return new ResponseEntity<Map<String, Iterable<Recipes>>>(result, HttpStatus.OK);
    }

}
