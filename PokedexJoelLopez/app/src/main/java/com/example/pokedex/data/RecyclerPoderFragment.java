package com.example.pokedex.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecyclerPoderFragment extends RecyclerPokedexFragment {
    @Override
    LiveData<List<Pokemon>> obtenerPokemons() {
        return pokemonsViewModel.masValorados();
    }
}