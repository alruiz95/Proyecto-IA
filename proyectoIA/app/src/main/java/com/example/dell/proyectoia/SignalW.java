package com.example.dell.proyectoia;

/**
 * Created by Alberto on 9/5/2017.
 */

public class SignalW {
    private String nombre;
    private int id;

    public SignalW(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
