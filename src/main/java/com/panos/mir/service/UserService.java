package com.panos.mir.service;

import com.panos.mir.exceptions.BadRequestException;
import com.panos.mir.exceptions.NotFoundException;
import com.panos.mir.model.Users;
import com.panos.mir.repositories.UserRepository;
import com.panos.mir.rootnames.ApiRootElementNames;
import com.panos.mir.rootnames.CustomJsonRootName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Map<String, Users> getUsers(){
        List<Users> users = repository.findAll();
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).users(), users);
        return result;
    }

    public ResponseEntity<Users> createUser(Users user){
        if (repository.findFirstByUsername(user.getUsername()) == null) {
            Users saved = repository.save(user);
            return new ResponseEntity<Users>(saved, HttpStatus.CREATED);
        } else {
            throw new BadRequestException();
        }
    }

    public ResponseEntity<Users> findUser(Users user){
        if (repository.findFirstByUsernameAndPassword(user.getUsername(), user.getPassword()) != null) {
            return new ResponseEntity<>(repository.findFirstByUsernameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
        } else {
            throw new NotFoundException();
        }
    }
}
