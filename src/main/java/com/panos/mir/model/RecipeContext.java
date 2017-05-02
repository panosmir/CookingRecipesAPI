package com.panos.mir.model;

import java.util.Set;

/**
 * Created by Panos on 5/2/2017.
 */
public class RecipeContext {
    private Set<Recipes> recipes;
    private Set<Ingredients> ingredients;

    public RecipeContext() {
    }

    public RecipeContext(Set<Recipes> recipes, Set<Ingredients> ingredients) {
        this.recipes = recipes;
        this.ingredients = ingredients;
    }

    public Set<Recipes> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipes> recipes) {
        this.recipes = recipes;
    }

    public Set<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
}
