package com.example.pokedex.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pokemon {
    @PrimaryKey(autoGenerate = true)
    int id;
    String nombre;
    String descripcion;
    String atk1;
    String atk2;
    String atk3;
    String atk4;
    float poder;
    int imagen;

    public Pokemon(String nombre, String descripcion, String atk1, String atk2, String atk3, String atk4, int imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.atk1 = atk1;
        this.atk2 = atk2;
        this.atk3 = atk3;
        this.atk4 = atk4;
        this.imagen = imagen;
    }
}
