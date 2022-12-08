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
//T ODO Model-View-ViewModel Usarlo para acceso a datos.
//T ODO RecyclerView (Interacción con el ViewHolder)
//T ODO Usar Room para introducir y guardar datos.
//TODO HACER FILTRO POR TIPO (CREAR VARIABLE TIPO DENTRO DE CADA POKEMON)
//TODO PANTALLA DE USUARIO
//TODO MIRAR SI HAY ALGUNA MANERA DE QUE EN EL FORMULARIO NO SE DESCUAJERINGUE TODO