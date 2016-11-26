package com.example.a68.lsitpersonapplication.models;

import java.io.Serializable;

/**
 * Created by 68 on 26/11/2016.
 **/

public class Person implements Serializable{
    private String nombre;
    private String cargo;
    private int image;

    public Person(){}

    public Person(String nombre, String cargo, int image) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
