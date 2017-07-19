package com.panos.mir.controllers;

import com.panos.mir.model.Categories;
import com.panos.mir.repositories.CategoryRepository;
import com.panos.mir.rootnames.ApiRootElementNames;
import com.panos.mir.rootnames.CustomJsonRootName;
import com.panos.mir.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoryRepository mCategoryRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity<Map<String, Iterable<Categories>>> getAllCategories(){
        Map result = categoryService.findAllCategories();
        return new ResponseEntity<Map<String, Iterable<Categories>>>(result, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public @ResponseBody ResponseEntity<Map<String, Categories>> getCategoryById(@PathVariable("id") int id){
        Map result = categoryService.findCategoryById(id);
        return new ResponseEntity<Map<String, Categories>>(result, HttpStatus.OK);
    }
}
