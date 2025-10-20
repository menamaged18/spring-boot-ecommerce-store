package com.store.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "custom_user")
public class CustomUser {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public CustomUser(){};
    public CustomUser(long _id, String _name, String _email, String _password){
        this.id = _id;
        this.email = _email;
        this.name = _name;
        this.password = _password;
    };

    public void setName(String _name){
        this.name = _name;
    }

    public void setEmail(String _email){
        this.email = _email;
    }

    public void setPassword(String _password){
        this.password = _password;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
