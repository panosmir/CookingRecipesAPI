package com.panos.mir;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by Panos on 3/22/2017.
 */
@Repository
public interface RecipesRepository extends CrudRepository<Recipes, Integer>{

//
//    @Query(value = "SELECT * FROM recipes WHERE user_id = ?1", nativeQuery = true)
//    List<Recipes> findUserRecipes(int id);

//    @Query("select r from Recipes r where r.title like %?1")
//    List<Recipes> findByTitleLikeIgnoreCase(String title);

    List<Recipes> findAllByTitleIsLike(String title);

//    @Query("select r from Recipes r where r.id=?1")
//    List<Recipes> findOneId(int id);

    List<Recipes> findById(int id);

    @Query(value = "SELECT * FROM recipes WHERE user_id=?1", nativeQuery = true)
    List<Recipes> findAllByUserUser_id(int id);

    @Query(value = "SELECT * FROM favorites, recipes, users WHERE favorites.user_id=users.user_id AND favorites.id=recipes.id", nativeQuery = true)
    List<Recipes> findFavorites();

//    @Query(value = "INSERT INTO favorites(favorites.user_id, favorites.id) VALUES (?1, ?2)", nativeQuery = true)
//    Recipes addFavorites(int id, int user_id);
//    Remove comments in order to work the android app.
//    @Query("select r from Recipes r where r.userId=?1")
//    List<Recipes> findUserId(int id);

}
