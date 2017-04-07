package com.panos.mir.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Panos on 3/30/2017.
 */
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
    @JoinTable(name = "favorites", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<Recipes> user_favorites;

    public Users() {
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
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

}
