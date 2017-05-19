package com.panos.mir.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Panos on 5/2/2017.
 */
@Entity
@Table(name = "ingredients")
public class Ingredients {
    @Column(name = "ingredients_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ingredient")
    private String ingredient;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipes> recipes = new HashSet<>();

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
    public Set<Recipes> getRecipes() {
        return recipes;
    }

    public Categories getCategory() {
        return category;
    }
}
