package com.example.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokedex.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
//T ODO Navigation (usando NavController) 2 Pantallas minimo
//T ODO Elementos de Menú (1 mínimo) Appbar, NavView,BottomNavView...
//TODO Model-View-ViewModel Usarlo para acceso a datos.
//TODO RecyclerView (Interacción con el ViewHolder)
//TODO Usar Room para introducir y guardar datos.