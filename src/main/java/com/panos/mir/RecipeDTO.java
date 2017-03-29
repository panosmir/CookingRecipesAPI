package com.panos.mir;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Panos on 3/22/2017.
 */
@CustomJsonRootName(singular = "recipe", plural = "recipes")
public class RecipeDTO {

    @Autowired
    private RecipesRepository recipesRepository;

    public Object findAll(){
        return recipesRepository.findAll();
    }
}
