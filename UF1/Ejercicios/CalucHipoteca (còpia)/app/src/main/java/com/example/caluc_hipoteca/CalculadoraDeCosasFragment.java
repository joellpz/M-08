package com.example.caluc_hipoteca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.caluc_hipoteca.databinding.FragmentMiHipotecaBinding;

public class CalculadoraDeCosasFragment extends Fragment {
    private FragmentMiHipotecaBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMiHipotecaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final CalculadoradeCosasViewModel calculadoradeCosasViewModel = new ViewModelProvider(this).get(CalculadoradeCosasViewModel.class);

        binding.sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;

                double first = 0;
                double second = 0;

                try {
                    first = Double.parseDouble(binding.first.getText().toString());
                } catch (Exception e){
                    binding.first.setError("Introduzca un número inferior a 100000");
                    error = true;
                }

                try {
                    second = Double.parseDouble(binding.second.getText().toString());
                } catch (Exception e){
                    binding.second.setError("Introduzca un número inferior a 100000");
                    error = true;
                }

                if (!error) {
                    calculadoradeCosasViewModel.calcular_suma(first, second);
                }
            }
        });

        binding.restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;

                double first = 0;
                double second = 0;

                try {
                    first = Double.parseDouble(binding.first.getText().toString());
                } catch (Exception e){
                    binding.first.setError("Introduzca un número inferior a 100000");
                    error = true;
                }

                try {
                    second = Double.parseDouble(binding.second.getText().toString());
                } catch (Exception e){
                    binding.second.setError("Introduzca un número inferior a 100000");
                    error = true;
                }

                if (!error) {
                    calculadoradeCosasViewModel.calcular_restar(first, second);
                }
            }
        });

        binding.multiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;

                double first = 0;
                double second = 0;

                try {
                    first = Double.parseDouble(binding.first.getText().toString());
                } catch (Exception e){
                    binding.first.setError("Introduzca un número inferior a 100000");
                    error = true;
                }

                try {
                    second = Double.parseDouble(binding.second.getText().toString());
                } catch (Exception e){
                    binding.second.setError("Introduzca un número inferior a 100000");
                    error = true;
                }

                if (!error) {
                    calculadoradeCosasViewModel.calcular_multiplicar(first, second);
                }
            }
        });

        binding.dividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;

                double fisrt = 0;
                double second = 0;

                try {
                    fisrt = Double.parseDouble(binding.first.getText().toString());
                } catch (Exception e){
                    binding.first.setError("Introduzca un número inferior a 100000");
                    error = true;
                }

                try {
                    second = Double.parseDouble(binding.second.getText().toString());
                } catch (Exception e){
                    binding.second.setError("Introduzca un número inferior a 100000");
                    error = true;
                }

                if (!error) {
                    calculadoradeCosasViewModel.calcular_dividir(fisrt, second);
                }
            }
        });

        calculadoradeCosasViewModel.result.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double result) {
                binding.result.setText(String.format("%.2f",result));
            }
        });

        calculadoradeCosasViewModel.errorFirst.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double capitalMinimo) {
                if (capitalMinimo != null) {
                    binding.first.setError("Introduzca un número inferior a " +capitalMinimo);
                } else {
                    binding.first.setError(null);
                }
            }
        });

        calculadoradeCosasViewModel.errorSecond.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double plazoMinimo) {
                if (plazoMinimo != null) {
                    binding.second.setError("Introduzca un número inferior a " + plazoMinimo);
                } else {
                    binding.second.setError(null);
                }
            }
        });

        calculadoradeCosasViewModel.calculando.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean calculando) {
                if (calculando) {
                    binding.calculando.setVisibility(View.VISIBLE);
                    binding.result.setVisibility(View.GONE);
                } else {
                    binding.calculando.setVisibility(View.GONE);
                    binding.result.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}