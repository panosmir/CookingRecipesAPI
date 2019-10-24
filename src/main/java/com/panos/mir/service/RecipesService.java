package com.panos.mir.service;

import com.panos.mir.model.Ingredients;
import com.panos.mir.model.RecipeDTO;
import com.panos.mir.model.RecipeIngredients;
import com.panos.mir.model.Recipes;
import com.panos.mir.repositories.RecipesRepository;
import com.panos.mir.rootnames.ApiRootElementNames;
import com.panos.mir.rootnames.CustomJsonRootName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipesService {

    @Autowired
    private RecipesRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    private Recipes recipes;


    public Map<String, Iterable<Recipes>> getAllRecipes() {
        List<Recipes> recipes = repo.findAll();
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), recipes);
        return result;
    }

    public Map<String, Iterable<Recipes>> findUserRecipes(int id) {
        List<Recipes> recipes = repo.findAllByUserUser_id(id);
        Map result = new HashMap();
        result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), recipes);
        return result;
    }

    public Recipes createRecipe(RecipeDTO recipe) {
        if (recipe.getUser() != null) {
            recipes = new Recipes(true);
            if (repo.findById(recipes.getId()) == null) {
                recipes.setTitle(recipe.getTitle());
                recipes.setDescription(recipe.getDescription());
                recipes.setUser(recipe.getUser());
                persistRecipe(recipe, recipes);
//                entityManager.merge(recipes);
                entityManager.flush();
            }
        }
        return recipes;
    }

    private void persistRecipe(RecipeDTO recipe, Recipes recipes) {
        recipe.getIngredients().forEach(ingredient -> {
            Ingredients ingredients = new Ingredients();
            ingredients.setId(ingredient.getId());
            ingredients.setIngredient(ingredient.getIngredient());
            ingredients.setCategory(ingredient.getCategories());
            RecipeIngredients recipeIngredients = new RecipeIngredients(recipes, ingredients, ingredient.getQuantity());
            recipes.getIngredients().add(recipeIngredients);
            entityManager.persist(recipes);
            entityManager.persist(recipeIngredients);
        });
    }

    public Map<String, Iterable<Recipes>> findRecipesByTitle(String title) {
        List<Recipes> recipes = repo.findAllByTitleIsLike("%" + title + "%");
        if (!recipes.isEmpty()) {
            Map result = new HashMap();
            result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), recipes);
            return result;
        } else
            return null;
    }

    public Boolean deleteRecipe(Recipes recipe) {
        if (repo.findRecipesByUserAndId(recipe.getUser().getUser_id(), recipe.getId()) != null) {
            repo.delete(recipe);
            return true;
        } else
            return null;
    }

    public Map<String, Recipes> updateRecipe(RecipeDTO recipe) {
        if (repo.findRecipesByUserAndId(recipe.getUser().getUser_id(), recipe.getId()) != null) {
            Recipes recipes = new Recipes(false);
            recipes.setId(recipe.getId());
            recipes.setTitle(recipe.getTitle());
            recipes.setDescription(recipe.getDescription());
            recipes.setUser(recipe.getUser());

            repo.delete(recipes);

            persistRecipe(recipe, recipes);
            entityManager.flush();

            Map result = new HashMap();
            result.put(ApiRootElementNames.class.getAnnotation(CustomJsonRootName.class).recipes(), recipes);
            return result;
        }
        return null;
    }

}
