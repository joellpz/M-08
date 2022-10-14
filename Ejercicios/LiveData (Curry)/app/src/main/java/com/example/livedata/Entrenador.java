package com.example.livedata;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

import androidx.lifecycle.LiveData;

public class Entrenador {

    interface EntrenadorListener {
        void cuandoDeLaOrden(String orden);
    }

    Random random = new Random();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> entrenando;

    void iniciarEntrenamiento(EntrenadorListener entrenadorListener) {
        if (entrenando == null || entrenando.isCancelled()) {
            entrenando = scheduler.scheduleAtFixedRate(new Runnable() {
                int tiro;
                int repeticiones = -1;

                @Override
                public void run() {
                    if (repeticiones < 0) {
                        repeticiones = 6;
                        tiro = 1;
                    }
                    entrenadorListener.cuandoDeLaOrden("TIRO" + tiro + ":" + (repeticiones == 0 ? "CANASTA" : repeticiones));
                    repeticiones--;
                    tiro++;
                }
            }, 0, 1, SECONDS);
        }
    }

    void pararEntrenamiento() {
        if (entrenando != null) {
            entrenando.cancel(true);
        }
    }
    LiveData<String> ordenLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            iniciarEntrenamiento(new EntrenadorListener() {
                @Override
                public void cuandoDeLaOrden(String orden) {
                    postValue(orden);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();

            pararEntrenamiento();
        }
    };
}