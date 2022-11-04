package com.company.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.company.recyclerview.databinding.FragmentMostrarPokemonBinding;

import java.util.Locale;


public class MostrarPokemonFragment extends Fragment {
    private FragmentMostrarPokemonBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarPokemonBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PokemonsViewModel pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonsViewModel.class);

        pokemonViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Pokemon>() {
            @Override
            public void onChanged(Pokemon pokemon) {
                binding.nombre.setText(pokemon.nombre.toUpperCase(Locale.ROOT));
                binding.descripcion.setText(pokemon.descripcion);
                binding.poder.setRating(pokemon.poder);
                binding.atk1.setText(pokemon.atk1);
                binding.atk2.setText(pokemon.atk2);
                binding.atk3.setText(pokemon.atk3);
                binding.atk4.setText(pokemon.atk4);
                binding.image.setImageResource(getResources().getIdentifier(pokemon.nombre, "drawable", "com.company.recyclerview"));


                binding.poder.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        if(fromUser){
                            pokemonViewModel.actualizar(pokemon, rating);
                        }
                    }
                });
            }
        });

    }
}