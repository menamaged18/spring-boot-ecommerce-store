package com.store.ecommerce.DTOs.User;

public class UserRequest {
    private Long id;
    private String username;
    private String email;
    private String password;
    
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

    public String getPassword(){
        return this.password;
    }

    public Long getId(){
        return this.id;
    }

}
