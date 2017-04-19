package com.panos.mir.repositories;

import com.panos.mir.model.Recipes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Panos on 3/22/2017.
 */
@Repository
public interface RecipesRepository extends CrudRepository<Recipes, Integer>{

    List<Recipes> findAllByTitleIsLike(String title);

    List<Recipes> findById(int id);

    @Query(value = "SELECT * FROM recipes WHERE user_id=?1", nativeQuery = true)
    List<Recipes> findAllByUserUser_id(int id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM recipes WHERE user_id=?1 AND id=?2", nativeQuery = true)
    void deleteByUserUser_id(int userId, int id);

}
