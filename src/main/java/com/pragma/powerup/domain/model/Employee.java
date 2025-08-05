package com.pragma.powerup.domain.model;

public class Employee {

    private String name;
    private String lastName;
    private String idNumber;
    private String email;
    private Role role;
    private String password;

    public Employee(String name, String lastName, String idNumber, String email, Role role, String password) {
        this.name = name;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
