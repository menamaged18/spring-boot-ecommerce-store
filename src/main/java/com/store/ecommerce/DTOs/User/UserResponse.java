package com.store.ecommerce.DTOs.User;

import com.store.ecommerce.model.CustomUser;

public class UserResponse {
    private Long id;
    private String username;
    private String email;

    public UserResponse(){}

    public UserResponse(CustomUser _CustomUser){
        this.id = _CustomUser.getId();
        this.email = _CustomUser.getEmail();
        this.username = _CustomUser.getName();
    }

    public Long getId(){
        return this.id;
    }
    
    public void setName(String _name){
        this.username = _name;
    }

    public void setEmail(String _email){
        this.email = _email;
    }

    public String getName(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }
}
