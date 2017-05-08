package com.panos.mir.repositories;

import com.panos.mir.model.Ingredients;
import com.panos.mir.model.Recipes;
import com.panos.mir.model.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

/**
 * Created by Panos on 3/22/2017.
 */
public interface RecipesRepository extends CrudRepository<Recipes, Integer>{

    List<Recipes> findAllByTitleIsLike(String title);

    List<Recipes> findById(int id);

    @Query(value = "SELECT * FROM recipes WHERE user_id=?1", nativeQuery = true)
    List<Recipes> findAllByUserUser_id(int id);

    @Query(value = "SELECT * FROM recipes WHERE user_id=?1 AND id=?2", nativeQuery = true)
    Recipes findRecipesByUserAndId(int userId, int id);

    @Query("select r.ingredients from Recipes r where r.id=:id")
    Set<Ingredients> getIngredientsByRecipesId(@Param("id") int id);

}
