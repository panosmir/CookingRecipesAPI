package com.panos.mir;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Panos on 3/21/2017.
 */
@Controller
@RequestMapping(path = "/recipes")
public class RecipesController {

    @Autowired
    private RecipesRepository repo;

    @GetMapping(path = "/all")
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Recipes>>> getAllRecipes(){
        List<Recipes> recipes = (List<Recipes>) repo.findAll();
        Map result = new HashMap();
        result.put(RecipeDTO.class.getAnnotation(CustomJsonRootName.class).plural(), recipes);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/all/{id}")
    public @ResponseBody ResponseEntity<Map<String, Recipes>> findRecipe(@PathVariable ("id") int id){
        List<Recipes> recipes = repo.findOne(id);
        Map result = new HashMap();
        result.put(RecipeDTO.class.getAnnotation(CustomJsonRootName.class).plural(), recipes);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/all/findbyTitle/{title}")
    public @ResponseBody ResponseEntity<Map<String, Iterable<Recipes>>> getRecipesByTitle(@PathVariable("title") String title){
        List<Recipes> recipes = repo.findByTitleLike(title);
        Map result = new HashMap();
        result.put(RecipeDTO.class.getAnnotation(CustomJsonRootName.class).plural(), recipes);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
