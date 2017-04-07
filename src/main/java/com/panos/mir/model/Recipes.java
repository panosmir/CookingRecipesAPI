package com.panos.mir.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipes {

//    Remove comments for user_id
//    @Column(name = "user_id")
//    private int user_id;

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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Recipes(int id, String title, String description) {
//        this.user_id = user_id;
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Recipes() {
    }

//    public int getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }

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
