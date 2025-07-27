package com.pragma.powerup.domain.model;

public enum Role {

    ADMIN("Administrador"),
    CUSTOMER("Cliente"),
    OWNER("Dueño"),
    EMPLOYEE("Empleado");

    private final String description;

    Role(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
