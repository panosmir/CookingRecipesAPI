package com.panos.mir;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Panos on 3/22/2017.
 */
@Repository
public interface RecipesRepository extends CrudRepository<Recipes, Integer>{

    @Query("select r from Recipes r where r.title like %?1")
    List<Recipes> findByTitleLike(String title);

    @Query("select r from Recipes r where r.id=?1")
    List<Recipes> findOne(int id);

    @Query("select r from Recipes r where r.userId=?1")
    List<Recipes> findUserId(int id);
}
