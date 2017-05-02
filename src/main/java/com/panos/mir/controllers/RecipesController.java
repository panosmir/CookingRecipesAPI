package com.panos.mir.controllers;

import com.panos.mir.exceptions.BadRequestException;
import com.panos.mir.exceptions.NotFoundException;
import com.panos.mir.model.Ingredients;
import com.panos.mir.model.RecipeContext;
import com.panos.mir.model.Recipes;
import com.panos.mir.repositories.RecipesRepository;
import com.panos.mir.rootnames.ApiRootElementNames;
import com.panos.mir.rootnames.CustomJsonRootName;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by Panos on 3/21/2017.
 */


@Controller
@RequestMapping(path = "/recipes")
public class RecipesController {

    @Autowired
    private RecipesRepository repo;

    //Returns all the recipes that exists on the server
    @GetMapping(path = "/all")
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Recipes>>> getAllRecipes() {
        List<Recipes> recipes = (List<Recipes>) repo.findAll();
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), recipes);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Returns recipes by id (for testing purposes only)
    @GetMapping(path = "/all/{id}")
    public @ResponseBody
    ResponseEntity<Map<String, Recipes>> findRecipe(@PathVariable("id") int id) {
        List<Recipes> recipes = repo.findById(id);
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), recipes);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Returns recipes by userId. Recipes that a user created.
    @GetMapping(path = "/all/userId/{id}")
    public @ResponseBody
    ResponseEntity<Map<String, Recipes>> findUserRecipe(@PathVariable("id") int id) {
        List<Recipes> recipes = repo.findAllByUserUser_id(id);
        if (!recipes.isEmpty()) {
            Map result = new HashMap();
            result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), recipes);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            throw new NotFoundException();
        }
    }

    //Create a recipe if a user is logged in
    @PostMapping(path = "/all/userId/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recipes> createRecipe(@RequestBody Recipes recipes) {
        if (recipes.getUser() != null) {
            Recipes saved = repo.save(recipes);
            return new ResponseEntity<Recipes>(saved, HttpStatus.CREATED);
        } else {
            throw new BadRequestException();
        }
    }

    //Find a recipe by title. For searching capabilities.
    @GetMapping(path = "/all/findbyTitle/{title}")
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Recipes>>> getRecipesByTitle(@PathVariable("title") String title) {
        List<Recipes> recipesList = repo.findAllByTitleIsLike("%" + title + "%");
        if (!recipesList.isEmpty()) {
            Map result = new HashMap();
            result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), recipesList);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            throw new NotFoundException();
        }
    }

    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Recipes>>> deleteRecipe(@RequestBody Recipes recipes) {
        if (repo.findRecipesByUserAndId(recipes.getUser().getUser_id(), recipes.getId())!=null ) {
            repo.delete(recipes);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            throw new BadRequestException();
        }
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Recipes> updateRecipe(@RequestBody Recipes recipe){
        if(repo.findRecipesByUserAndId(recipe.getUser().getUser_id(), recipe.getId())!=null) {
            Recipes updatedRecipe = repo.save(recipe);
            Map result = new HashMap();
            result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), updatedRecipe);
            return new ResponseEntity<>(recipe, HttpStatus.CREATED);
        }
        else{
            throw new BadRequestException();
        }
    }

    @GetMapping("/ingredients/{id}")
    public @ResponseBody ResponseEntity<Map<String, Iterable<Ingredients>>> getIngredients(@PathVariable("id") int id){
        Set<Ingredients> ingredients = repo.getIngredientsByRecipesId(id);
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), ingredients);
        return new ResponseEntity<Map<String, Iterable<Ingredients>>>(result, HttpStatus.OK);
    }

}
