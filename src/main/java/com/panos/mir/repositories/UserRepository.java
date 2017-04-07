package com.panos.mir.repositories;

import com.panos.mir.model.Recipes;
import com.panos.mir.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by Panos on 3/30/2017.
 */
public interface UserRepository extends CrudRepository<Users, Integer> {

    Users findFirstByUsernameAndPassword(String username, String password);

    Users findFirstByUsernameOrPassword(String username, String password);

}
