package com.example.pokedex;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokedex.databinding.FragmentBienvenidoBinding;
import com.example.pokedex.databinding.FragmentIntroBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BienvenidoFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class BienvenidoFragment extends Fragment {

    private FragmentBienvenidoBinding binding;
    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentBienvenidoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_bienvenidoFragment2_to_pokedexActivity);
            }
        });
    }

}