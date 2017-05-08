package com.panos.mir.model;

import java.util.Set;

public class UserContext {
    private Recipes recipe;
    private Users user;

    public UserContext() {
    }

    public Recipes getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipes recipe) {
        this.recipe = recipe;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}