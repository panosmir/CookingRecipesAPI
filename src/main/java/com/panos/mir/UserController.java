package com.panos.mir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Panos on 3/30/2017.
 */
@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Users>>> getUsers(){
        List<Users> users = (List<Users>) repository.findAll();
        Map result = new HashMap();
        result.put("users", users);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> create(@RequestBody Users user){
        if (repository.findFirstByUsernameOrPassword(user.getUsername(), user.getPassword()) == null){
            Users saved = repository.save(user);
            return new ResponseEntity<Users>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/all/findUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> findUser(@RequestBody Users user) throws NoResultException{
        if (repository.findFirstByUsernameAndPassword(user.getUsername(), user.getPassword()) !=null){
            return new ResponseEntity<Users>(repository.findFirstByUsernameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
        }
        throw new NotFoundException();
    }

}
