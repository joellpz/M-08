package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

//TODO Navigaton (usando NavController) 2 Pantallas minimo
//TODO Elementos de Menú (1 mínimo) Appbar, NavView,BottomNavView...
//TODO Model-View-ViewModel Usarlo para acceso a datos.
//TODO RecyclerView (Interacción con el ViewHolder)
//TODO Usar Room para introducir y guardar datos.