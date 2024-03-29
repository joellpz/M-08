package com.company.recyclerview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.recyclerview.databinding.FragmentNuevoPokemonBinding;


public class NuevoPokemonFragment extends Fragment {
    private FragmentNuevoPokemonBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoPokemonBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PokemonsViewModel pokemonsViewModel = new ViewModelProvider(requireActivity()).get(PokemonsViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();
                String atk1 = binding.atk1.getText().toString();
                String atk2 = binding.atk2.getText().toString();
                String atk3 = binding.atk3.getText().toString();
                String atk4 = binding.atk4.getText().toString();

                pokemonsViewModel.insertar(new Pokemon(nombre, descripcion, atk1, atk2, atk3, atk4));

                navController.popBackStack();
            }
        });
    }
}