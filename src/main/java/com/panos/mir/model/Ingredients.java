package com.panos.mir.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Panos on 5/2/2017.
 */
@Entity
@Table(name = "ingredients")
public class Ingredients {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ingredient")
    private String ingredient;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipes> recipes = new HashSet<>();

    public Ingredients() {
    }

    public Ingredients(int id, String ingredient) {
        this.id = id;
        this.ingredient = ingredient;
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

}
