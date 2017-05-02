package com.panos.mir.repositories;

import com.panos.mir.model.Ingredients;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Created by Panos on 5/2/2017.
 */
public interface IngredientsRepository extends CrudRepository<Ingredients, Integer> {

    @Query("select ingredients from Ingredients ingredients where ingredients.id=:id")
    Set<Ingredients> getRecipesByIngredientsId(@Param("id") int id);
}
