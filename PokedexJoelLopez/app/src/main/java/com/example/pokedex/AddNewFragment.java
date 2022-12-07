package com.example.pokedex;

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

import com.example.pokedex.databinding.FragmentAddNewBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNewFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewFragment extends Fragment {

    private FragmentAddNewBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentAddNewBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ElementosViewModel elementosViewModel = new ViewModelProvider(requireActivity()).get(ElementosViewModel.class);

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();

                elementosViewModel.insertar(new Elemento(nombre, descripcion));

                
            }
        });

    }
}