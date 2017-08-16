package com.panos.mir.controllers;

import com.panos.mir.exceptions.BadRequestException;
import com.panos.mir.exceptions.NotFoundException;
import com.panos.mir.model.*;
import com.panos.mir.repositories.RecipesRepository;
import com.panos.mir.repositories.UserRepository;
import com.panos.mir.rootnames.ApiRootElementNames;
import com.panos.mir.rootnames.CustomJsonRootName;
import com.panos.mir.service.RecipesService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@RestController
@RequestMapping(path = "/recipes")
public class RecipesController {

    @Autowired
    private RecipesRepository repo;
    @Autowired
    private UserRepository mUserRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RecipesService recipesService;

    //Returns all the recipes that exists on the server
    @GetMapping(path = "/all")
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Recipes>>> getAllRecipes() {
        Map recipes = recipesService.getAllRecipes();
        return new ResponseEntity<Map<String, Iterable<Recipes>>>(recipes, HttpStatus.OK);
    }

    //Returns recipes by userId. Recipes that a user created.
    @GetMapping(path = "/all/userId/{id}")
    public @ResponseBody
    ResponseEntity<Map<String, Recipes>> findUserRecipe(@PathVariable("id") int id) {
        Map recipes = recipesService.findUserRecipes(id);
        if (!recipes.isEmpty()) {
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } else {
            throw new NotFoundException();
        }
    }

    //Create a recipe if a user is logged in
    @PostMapping(path = "/all/userId/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Recipes> createRecipe(@RequestBody Recipe recipe) {
        Recipes saved = recipesService.createRecipe(recipe);
        if (saved != null)
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        else
            throw new BadRequestException();
    }

    //Find a recipe by title. For searching capabilities.
    @GetMapping(path = "/all/findbyTitle/{title}")
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Recipes>>> getRecipesByTitle(@PathVariable("title") String title) {
        Map recipes = recipesService.findRecipesByTitle(title);
        if (!recipes.isEmpty()) {
            return new ResponseEntity<Map<String, Iterable<Recipes>>>(recipes, HttpStatus.OK);
        } else
            throw new NotFoundException();
    }

    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public @ResponseBody
    ResponseEntity<String> deleteRecipe(@RequestBody Recipes recipes) {
        if (recipesService.deleteRecipe(recipes) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new BadRequestException();
        }
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(propagation = Propagation.REQUIRED)
    public @ResponseBody
    ResponseEntity<Recipes> updateRecipe(@RequestBody Recipe recipe) {
        Map recipes = recipesService.updateRecipe(recipe);
        if (recipes != null) {
            return new ResponseEntity(recipes, HttpStatus.CREATED);
        } else {
            throw new BadRequestException();
        }
    }

    //This code needs to be fixed and then move it to recipesService. This will take place after the ver 1.0 release.
    //// TODO: 17-Jul-17 To be fixed because now there is CompositeKey Table.
    @GetMapping("/ingredients/{recipe_id}")
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Ingredients>>> getIngredients(@PathVariable("recipe_id") int id) {
        Set<Ingredients> ingredients = repo.getIngredientsByRecipesId(id);
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), ingredients);
        return new ResponseEntity<Map<String, Iterable<Ingredients>>>(result, HttpStatus.OK);
    }

    @GetMapping("/userFavorites/{id}")
    public ResponseEntity<Map<String, Iterable<Recipes>>> getUserFavorites(@PathVariable("id") int id) {
        List<Recipes> favRecipes = repo.getUserFavorites(id);
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), favRecipes);
        return new ResponseEntity<Map<String, Iterable<Recipes>>>(result, HttpStatus.OK);
    }

    //// TODO: 18-May-17 Change the way the data inserts into database.
    @GetMapping(path = "userFavorites/add/{id}/{username}")
    @Transactional
    public ResponseEntity addFavorites(@PathVariable int id, @PathVariable String username) {


        if (mUserRepository.findFirstByUsername(username) != null && repo.findById(id)!=null) {

            Recipes recipes = repo.findById(id);
            Users users = mUserRepository.findFirstByUsername(username);

            recipes.getFavorites().add(users);
            users.getUser_favorites().add(recipes);
            repo.save(recipes);
            mUserRepository.save(users);

            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            throw new BadRequestException();
        }
    }

    @GetMapping("/removeFavorite")
    @Transactional
    public ResponseEntity removeFavorites(@RequestParam int id, @RequestParam String username) {


        if (mUserRepository.findFirstByUsername(username) != null && repo.findById(id)!=null) {

            Recipes recipes = repo.findById(id);
            Users users = mUserRepository.findFirstByUsername(username);

            recipes.getFavorites().remove(users);
            users.getUser_favorites().remove(recipes);
            repo.save(recipes);
            mUserRepository.save(users);

            return new ResponseEntity(HttpStatus.OK);

        } else {
            throw new BadRequestException();
        }
    }

}
