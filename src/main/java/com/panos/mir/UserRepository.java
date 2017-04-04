package com.panos.mir;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Panos on 3/30/2017.
 */
public interface UserRepository extends CrudRepository<Users, Integer> {

    Users findFirstByUsernameAndPassword(String username, String password);

    Users findFirstByUsernameOrPassword(String username, String password);

}
