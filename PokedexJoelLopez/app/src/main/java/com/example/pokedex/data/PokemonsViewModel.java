package com.example.pokedex.data;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PokemonsViewModel extends AndroidViewModel {

    PokemonRepositorio pokemonRepositorio;

    MutableLiveData<Pokemon> pokemonSeleccionado = new MutableLiveData<>();

    public PokemonsViewModel(@NonNull Application application) {
        super(application);

        pokemonRepositorio = new PokemonRepositorio(application);
        pokemonRepositorio.meterPokemons();
    }

    LiveData<List<Pokemon>> obtener(){
        return pokemonRepositorio.obtener();
    }


    void insertar(Pokemon pokemon){
        pokemonRepositorio.insertar(pokemon);
    }

    void eliminar(Pokemon pokemon){
        pokemonRepositorio.eliminar(pokemon);
    }

    void actualizar(Pokemon pokemon, float valoracion){
        pokemonRepositorio.actualizar(pokemon, valoracion);
    }
    void seleccionar(Pokemon elemento){
        pokemonSeleccionado.setValue(elemento);
    }

    MutableLiveData<Pokemon> seleccionado(){
        return pokemonSeleccionado;
    }

    LiveData<List<Pokemon>> masValorados(){
        return pokemonRepositorio.masValorados();
    }
}
