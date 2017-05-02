package com.panos.mir.controllers;

import com.panos.mir.model.Ingredients;
import com.panos.mir.model.RecipeContext;
import com.panos.mir.model.Recipes;
import com.panos.mir.repositories.IngredientsRepository;
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
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), ingredients);
        return new ResponseEntity<Map<String, Iterable<Ingredients>>>(result, HttpStatus.OK);
    }


}
