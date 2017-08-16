package com.panos.mir.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipes implements Serializable {

    @Column(name = "recipes_id")
    @Id
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user;

    @ManyToMany
    @JoinTable(name = "favorites", joinColumns = @JoinColumn(name = "recipes_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Users> favorites = new HashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeIngredients> ingredients = new HashSet<>();

    public Recipes(boolean withId) {
        if (withId)
            id = new Random().nextInt(2000) + 1;
    }

    public Recipes() {
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

    public Set<RecipeIngredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<RecipeIngredients> ingredients) {
        this.ingredients = ingredients;
    }

    public Users getUser() {
        return user;
    }

    public Set<Users> getFavorites() {
        return favorites;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setFavorites(Set<Users> favorites) {
        this.favorites = favorites;
    }
}
