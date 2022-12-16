package com.example.livedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class EntrenadorViewModel extends AndroidViewModel {
    Entrenador entrenador;

    LiveData<Integer> faseLiveData;
    LiveData<String> repeticionLiveData;

    public EntrenadorViewModel(@NonNull Application application) {
        super(application);

        entrenador = new Entrenador();

        faseLiveData = Transformations.switchMap(entrenador.ordenLiveData, new Function<String, LiveData<Integer>>() {

            String faseAnterior;

            @Override
            public LiveData<Integer> apply(String orden) {

                String ejercicio = orden.split(":")[0];

                if(!ejercicio.equals(faseAnterior)){
                    faseAnterior = ejercicio;
                    int imagen;
                    switch (ejercicio) {
                        case "TIRO1":
                        default:
                            imagen = R.drawable.tiro1;
                            break;
                        case "TIRO2":
                            imagen = R.drawable.tiro2;
                            break;
                        case "TIRO3":
                            imagen = R.drawable.tiro3;
                            break;
                        case "TIRO4":
                            imagen = R.drawable.tiro4;
                            break;
                        case "TIRO5":
                            imagen = R.drawable.tiro5;
                            break;
                        case "TIRO6":
                            imagen = R.drawable.tiro6;
                            break;
                    }

                    return new MutableLiveData<>(imagen);
                }
                return null;
            }
        });

        repeticionLiveData = Transformations.switchMap(entrenador.ordenLiveData, new Function<String, LiveData<String>>() {
            @Override
            public LiveData<String> apply(String orden) {
                return new MutableLiveData<>(orden.split(":")[1]);
            }
        });
    }

    LiveData<Integer> obtenerEjercicio(){
        return faseLiveData;
    }

    LiveData<String> obtenerRepeticion(){
        return repeticionLiveData;
    }
}