package com.example.pokedex.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.pokedex.R;
import com.example.pokedex.databinding.FragmentMostrarPokemonBinding;

import java.util.Locale;


public class MostrarPokemonFragment extends Fragment {
    private FragmentMostrarPokemonBinding binding;
    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarPokemonBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PokemonsViewModel elementosViewModel = new ViewModelProvider(requireActivity()).get(PokemonsViewModel.class);
        navController = Navigation.findNavController(view);

        binding.btnBack.setOnClickListener(view1 -> navController.navigate(R.id.action_recyclerPokedexFragment));

        elementosViewModel.seleccionado().observe(getViewLifecycleOwner(), pokemon -> {
            binding.nombre.setText(pokemon.nombre.toUpperCase(Locale.ROOT));
            binding.descripcion.setText(pokemon.descripcion);
            binding.poder.setRating(pokemon.poder);
            binding.atk1.setText(pokemon.atk1);
            binding.atk2.setText(pokemon.atk2);
            binding.atk3.setText(pokemon.atk3);
            binding.atk4.setText(pokemon.atk4);
            binding.image.setImageResource(pokemon.imagen);

            binding.poder.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
                if(fromUser){
                    elementosViewModel.actualizar(pokemon, rating);
                }
            });
        });
    }
}