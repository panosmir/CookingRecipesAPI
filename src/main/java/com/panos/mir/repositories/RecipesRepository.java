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

    @Query(value = "SELECT * FROM favorites, recipes, users WHERE favorites.user_id=users.user_id AND favorites.id=recipes.id", nativeQuery = true)
    List<Recipes> findFavorites();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM recipes WHERE user_id=?1", nativeQuery = true)
    void deleteByUserUser_id(int id);

//    @Query(value = "INSERT INTO favorites(favorites.user_id, favorites.id) VALUES (?1, ?2)", nativeQuery = true)
//    Recipes addFavorites(int id, int user_id);
//    Remove comments in order to work the android app.
//    @Query("select r from Recipes r where r.userId=?1")
//    List<Recipes> findUserId(int id);

}
