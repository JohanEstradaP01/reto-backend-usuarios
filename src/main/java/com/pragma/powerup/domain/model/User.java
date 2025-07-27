package com.pragma.powerup.domain.model;

@SuppressWarnings("ALL")
public class User {

    private String name;
    private String email;
    private String password;
    private Role role;

    public User(String name, String email, String password, Role role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Role.CUSTOMER;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
