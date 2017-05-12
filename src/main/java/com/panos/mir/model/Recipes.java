package com.panos.mir.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipes {

    @Column(name = "recipes_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    @JsonManagedReference
    private Users user;

    @ManyToMany(mappedBy = "user_favorites")
//    @JsonBackReference
    private Set<Users> favorites = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recipes_has_ingredients",
            joinColumns = @JoinColumn(name = "recipes_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
//    @JsonManagedReference
    private Set<Ingredients> ingredients = new HashSet<>();

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

    public Set<Ingredients> getIngredients() {
        return ingredients;
    }

    public Users getUser() {
        return user;
    }

    public Set<Users> getFavorites() {
        return favorites;
    }
}
