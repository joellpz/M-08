package com.company.recyclerview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PokemonsViewModel extends AndroidViewModel {

    PokemonRepositorio pokemonRepositorio;

    MutableLiveData<List<Pokemon>> listElementosMutableLiveData = new MutableLiveData<>();

    MutableLiveData<Pokemon> pokemonSeleccionado = new MutableLiveData<>();

    public PokemonsViewModel(@NonNull Application application) {
        super(application);

        pokemonRepositorio = new PokemonRepositorio();

        listElementosMutableLiveData.setValue(pokemonRepositorio.obtener());
    }

    MutableLiveData<List<Pokemon>> obtener(){
        return listElementosMutableLiveData;
    }

    void insertar(Pokemon pokemon){
        pokemonRepositorio.insertar(pokemon, new PokemonRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Pokemon> pokemons) {
                listElementosMutableLiveData.setValue(pokemons);
            }
        });
    }

    void eliminar(Pokemon pokemon){
        pokemonRepositorio.eliminar(pokemon, new PokemonRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Pokemon> pokemons) {
                listElementosMutableLiveData.setValue(pokemons);
            }
        });
    }

    void actualizar(Pokemon pokemon, float valoracion){
        pokemonRepositorio.actualizar(pokemon, valoracion, new PokemonRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Pokemon> pokemons) {
                listElementosMutableLiveData.setValue(pokemons);
            }
        });
    }

    void seleccionar(Pokemon elemento){
        pokemonSeleccionado.setValue(elemento);
    }

    MutableLiveData<Pokemon> seleccionado(){
        return pokemonSeleccionado;
    }
}
