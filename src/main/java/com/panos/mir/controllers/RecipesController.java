package com.panos.mir.controllers;

import com.panos.mir.exceptions.BadRequestException;
import com.panos.mir.exceptions.NotFoundException;
import com.panos.mir.model.*;
import com.panos.mir.repositories.RecipesRepository;
import com.panos.mir.repositories.UserRepository;
import com.panos.mir.rootnames.ApiRootElementNames;
import com.panos.mir.rootnames.CustomJsonRootName;
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

    //Returns all the recipes that exists on the server
    @GetMapping(path = "/all")
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Recipes>>> getAllRecipes() {
        List<Recipes> recipes = repo.findAll();
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
    @Transactional
    public ResponseEntity<Recipes> createRecipe(@RequestBody Recipe recipe) {
        if (recipe.getUser() != null) {

            Recipes recipes = new Recipes(true);
            if(repo.findById(recipes.getId()).isEmpty()) {
                recipes.setTitle(recipe.getTitle());
                recipes.setDescription(recipe.getDescription());
                recipes.setUser(recipe.getUser());
                recipe.getIngredients().forEach(ingredient -> {
                    RecipeIngredients recipeIngredients = new RecipeIngredients(recipes, ingredient, ingredient.getQuantity());
                    recipes.getIngredients().add(recipeIngredients);
                    entityManager.persist(recipes);
                    entityManager.persist(recipeIngredients);
                });

                entityManager.flush();
                Recipes saved = repo.saveAndFlush(recipes);

                return new ResponseEntity<>(saved, HttpStatus.CREATED);
            }
            throw new BadRequestException();
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
        } else {
            throw new NotFoundException();
        }
    }

    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Recipes>>> deleteRecipe(@RequestBody Recipes recipes) {
        if (repo.findRecipesByUserAndId(recipes.getUser().getUser_id(), recipes.getId()) != null) {
            repo.delete(recipes);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new BadRequestException();
        }
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(propagation = Propagation.REQUIRED)
    public @ResponseBody
    ResponseEntity<Recipes> updateRecipe(@RequestBody Recipe recipe) {
        if (repo.findRecipesByUserAndId(recipe.getUser().getUser_id(), recipe.getId()) != null) {
            Recipes recipes = new Recipes(false);
            recipes.setId(recipe.getId());
            recipes.setTitle(recipe.getTitle());
            recipes.setDescription(recipe.getDescription());
            recipes.setUser(recipe.getUser());

            repo.delete(recipes);

            recipe.getIngredients().forEach(ingredient -> {
                RecipeIngredients recipeIngredients = new RecipeIngredients(recipes, ingredient, ingredient.getQuantity());
                recipes.getIngredients().add(recipeIngredients);
                entityManager.persist(recipes);
                entityManager.persist(recipeIngredients);
            });
//            repo.save(recipes);
            entityManager.flush();
//            entityManager.merge(recipes);

            Map result = new HashMap();
            result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), recipes);
            return new ResponseEntity<>(recipes, HttpStatus.CREATED);
        } else {
            throw new BadRequestException();
        }
    }

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
    @PostMapping(path = "userFavorites/add")
    @Transactional
    public ResponseEntity addFavorites(@RequestBody UserContext userContext) {
        Recipes recipes = userContext.getRecipe();
        Users users = userContext.getUser();

        if (mUserRepository.findFirstByUsernameAndPassword(users.getUsername(), users.getPassword()) != null) {
            recipes.getFavorites().add(users);
            users.getUser_favorites().add(recipes);

            entityManager.merge(users);
            entityManager.flush();

            Map result = new HashMap();
            result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), recipes);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } else {
            throw new BadRequestException();
        }
    }

    @PostMapping("/removeFavorite")
    @Transactional
    public ResponseEntity removeFavorites(@RequestBody UserContext userContext) {
        Users user = userContext.getUser();
        Recipes recipe = userContext.getRecipe();
        Set<Users> favorites = new HashSet<>(recipe.getFavorites());

        if (mUserRepository.findFirstByUsernameAndPassword(user.getUsername(), user.getPassword()) != null) {

            favorites.forEach(users -> {
                if (users.equals(user)) {
                    favorites.remove(user);
                    user.getUser_favorites().remove(recipe);
                    entityManager.merge(user);
                    entityManager.flush();
                }
            });

            Map result = new HashMap();
            result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), recipe);
            return new ResponseEntity(result, HttpStatus.OK);

        } else {
            throw new BadRequestException();
        }
    }

}
