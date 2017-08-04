package com.panos.mir.service;

import com.panos.mir.model.Ingredients;
import com.panos.mir.repositories.IngredientsRepository;
import com.panos.mir.rootnames.ApiRootElementNames;
import com.panos.mir.rootnames.CustomJsonRootName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IngredientsService {

    @Autowired
    private IngredientsRepository mIngredientsRepository;

    public Map<String, Ingredients> findIngredientsByCategoryId(int id){
        List<Ingredients> ingredientsList = mIngredientsRepository.findAllByCategory_Id(id);
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).ingredients(), ingredientsList);
        return result;
    }
}
