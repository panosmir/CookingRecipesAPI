package com.panos.mir.service;

import com.panos.mir.model.Categories;
import com.panos.mir.repositories.CategoryRepository;
import com.panos.mir.rootnames.ApiRootElementNames;
import com.panos.mir.rootnames.CustomJsonRootName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository mCategoryRepository;

    public Map<String, Categories> findAllCategories(){
        List<Categories> categories = (List<Categories>) mCategoryRepository.findAll();
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).categories(), categories);
        return result;
    }

    public Map<String, Categories> findCategoryById(int id){
        Categories category = mCategoryRepository.findOne(id);
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).categories(), category);
        return result;
    }
}
