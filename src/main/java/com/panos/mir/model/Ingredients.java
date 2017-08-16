package com.panos.mir.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredients implements Serializable{
    @Column(name = "ingredients_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ingredient")
    private String ingredient;

    @OneToMany(mappedBy = "ingredient")
    private Set<RecipeIngredients> recipes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Categories category;

    public Ingredients() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    @JsonIgnore
    public Set<RecipeIngredients> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeIngredients> recipes) {
        this.recipes = recipes;
    }

    public Categories getCategory() {
        return category;
    }

}
