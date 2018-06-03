package com.panos.mir.model;


import java.util.HashSet;
import java.util.Set;

public class RecipeDTO {
    private int id;
    private String title;
    private String description;
    private Users user;
    private Set<IngredientDTO> ingredients = new HashSet<>();

    public RecipeDTO(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public RecipeDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Set<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
