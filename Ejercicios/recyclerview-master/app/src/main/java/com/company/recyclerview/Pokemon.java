package com.company.recyclerview;

public class Pokemon {
    String nombre;
    String descripcion;
    String[] ataques = new String[4];
    float poder;

    public Pokemon(String nombre, String descripcion, String atk1, String atk2, String atk3, String atk4) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ataques[0] = atk1;
        this.ataques[1] = atk2;
        this.ataques[2] = atk3;
        this.ataques[3] = atk4;
    }
}
