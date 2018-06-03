package com.panos.mir.repositories;

import com.panos.mir.model.Recipes;
import com.panos.mir.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findFirstByUsernameAndPassword(String username, String password);

    Users findFirstByUsername(String username);

    @Query("select u.user_favorites from Users u where u.user_id=:id")
    Set<Recipes> getUserFavorites(@Param("id") int id);
}
