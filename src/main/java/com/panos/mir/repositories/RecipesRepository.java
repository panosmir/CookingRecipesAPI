package com.panos.mir.repositories;

import com.panos.mir.model.Ingredients;
import com.panos.mir.model.Recipes;
import com.panos.mir.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

public interface RecipesRepository extends JpaRepository<Recipes, Integer> {

    List<Recipes> findAllByTitleIsLike(String title);

    Recipes findById(int id);

    @Query(value = "SELECT * FROM recipes WHERE user_id=?1", nativeQuery = true)
    List<Recipes> findAllByUserUser_id(int id);

    @Query(value = "SELECT * FROM recipes WHERE user_id=?1 AND recipes_id=?2", nativeQuery = true)
    Recipes findRecipesByUserAndId(int userId, int id);

    @Query("select r.ingredients from Recipes r where r.id=:id")
    Set<Ingredients> getIngredientsByRecipesId(@Param("id") int id);

    @Query(value = "SELECT * FROM favorites, recipes, users WHERE" +
            " users.user_id=?1 AND favorites.recipes_id=recipes.recipes_id " +
            "AND favorites.user_id=users.user_id", nativeQuery = true)
    List<Recipes> getUserFavorites(@Param("id") int id);

}
