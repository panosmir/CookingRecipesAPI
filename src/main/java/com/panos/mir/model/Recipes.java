package com.panos.mir.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipes {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user;

    @ManyToMany(mappedBy = "user_favorites")
    private Set<Users> users;

    @ManyToMany
    @JoinTable(name = "recipes_has_ingredients", joinColumns = @JoinColumn(name = "recipes_id"), inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
    private Set<Ingredients> ingredients = new HashSet<>();

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Recipes(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

}
