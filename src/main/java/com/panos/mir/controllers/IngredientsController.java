package com.panos.mir.controllers;

import com.panos.mir.model.Ingredients;
import com.panos.mir.model.RecipeContext;
import com.panos.mir.model.Recipes;
import com.panos.mir.repositories.IngredientsRepository;
import com.panos.mir.rootnames.ApiRootElementNames;
import com.panos.mir.rootnames.CustomJsonRootName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Panos on 5/2/2017.
 */
@Controller
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    private IngredientsRepository mIngredientsRepository;

    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Ingredients>>> getIngredients(){
        List<Ingredients> ingredients = (List<Ingredients>) mIngredientsRepository.findAll();
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).ingredients(), ingredients);
        return new ResponseEntity<Map<String, Iterable<Ingredients>>>(result, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public @ResponseBody ResponseEntity<Map<String, Ingredients>> getIngredientsById(@PathVariable("id") int id){
        Ingredients ingredient = mIngredientsRepository.findOne(id);
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).ingredients(), ingredient);
        return new ResponseEntity<Map<String, Ingredients>>(result, HttpStatus.OK);
    }

    @GetMapping("/findByTitle/{title}")
    public @ResponseBody ResponseEntity<Map<String, Iterable<Ingredients>>> getIngredientsByTitle(@PathVariable("title") String title){
        List<Ingredients> ingredient = mIngredientsRepository.getByIngredientIsLike("%" + title + "%");
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).ingredients(), ingredient);
        return new ResponseEntity<Map<String, Iterable<Ingredients>>>(result, HttpStatus.OK);
    }

    @GetMapping("/findByCategoryId/{id}")
    public @ResponseBody ResponseEntity<Map<String, Iterable<Ingredients>>> getIngredientsByCategoryId(@PathVariable("id") int id){
        List<Ingredients> ingredientsList = mIngredientsRepository.findAllByCategory_Id(id);
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).ingredients(), ingredientsList);
        return new ResponseEntity<Map<String, Iterable<Ingredients>>>(result, HttpStatus.OK);
    }
}
