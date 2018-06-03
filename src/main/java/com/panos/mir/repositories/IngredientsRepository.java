package com.panos.mir.repositories;

import com.panos.mir.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {

    @Query("select ingredients from Ingredients ingredients where ingredients.id=:id")
    Set<Ingredients> getRecipesByIngredientsId(@Param("id") int id);

//    @Query("select ingredient from Ingredients ingredient where ingredient.ingredient=:title")
    List<Ingredients> getByIngredientIsLike(String title);

    List<Ingredients> findAllByCategory_Id(int id);

}
