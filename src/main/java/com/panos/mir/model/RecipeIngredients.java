package com.panos.mir.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.DeserializationConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.annotation.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "recipes_has_ingredients")
public class RecipeIngredients {

    public static class RecipeIngredientsId implements Serializable{
        protected int recipes_id;
        protected int ingredients_id;

        public RecipeIngredientsId(int recipes_id, int ingredients_id) {
            this.recipes_id = recipes_id;
            this.ingredients_id = ingredients_id;
        }

        public RecipeIngredientsId() {
        }

    }

    @EmbeddedId
    private RecipeIngredientsId id;

    @ManyToOne
    @JoinColumn(name = "recipes_id", insertable = false, updatable = false)
    protected Recipes recipe;

    @ManyToOne
    @JoinColumn(name = "ingredients_id", insertable = false, updatable = false)
    protected Ingredients ingredient;

    @Column(name = "quantity")
    protected String quantity;

    public RecipeIngredients(Recipes recipe, Ingredients ingredient, String quantity) {
        this.id = new RecipeIngredientsId(recipe.getId(), ingredient.getId());

        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;

        recipe.getIngredients().add(this);
        ingredient.getRecipes().add(this);

    }

    public RecipeIngredients() {
    }

    @JsonIgnore
    public RecipeIngredientsId getId() {
        return id;
    }

    public void setId(RecipeIngredientsId id) {
        this.id = id;
    }

    @JsonIgnore
    public Recipes getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipes recipe) {
        this.recipe = recipe;
    }

    @JsonUnwrapped
    public Ingredients getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredients ingredient) {
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
