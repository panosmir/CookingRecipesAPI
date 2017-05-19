package com.panos.mir.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Recipes> recipes;

    @ManyToMany
    @JoinTable(name = "favorites", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipes_id"))
    private Set<Recipes> user_favorites = new HashSet<>();

    public Users() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRecipes(Set<Recipes> recipes) {
        this.recipes = recipes;
    }

    public void setUser_favorites(Set<Recipes> user_favorites) {
        this.user_favorites = user_favorites;
    }

    @JsonIgnore
    public Set<Recipes> getUser_favorites() {
        return user_favorites;
    }

    @JsonIgnore
    public Set<Recipes> getRecipes() {
        return recipes;
    }
}
