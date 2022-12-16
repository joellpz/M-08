package com.example.caluc_hipoteca;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CalculadoradeCosasViewModel extends AndroidViewModel {

    Executor executor;

    SimuladorCalculadora simulador;

    MutableLiveData<Double> result = new MutableLiveData<>();
    MutableLiveData<Double> errorFirst = new MutableLiveData<>();
    MutableLiveData<Double> errorSecond = new MutableLiveData<>();
    MutableLiveData<Boolean> calculando = new MutableLiveData<>();

    public CalculadoradeCosasViewModel(@NonNull Application application) {
        super(application);

        executor = Executors.newSingleThreadExecutor();
        simulador = new SimuladorCalculadora();
    }

    public void calcular_suma(double first, double second) {

        final SimuladorCalculadora.Solicitud solicitud = new SimuladorCalculadora.Solicitud(first, second);

        executor.execute(new Runnable() {
            @Override
            public void run() {

                simulador.calcular_suma(solicitud, new SimuladorCalculadora.Callback() {
                    @Override
                    public void cuandoEsteCalculadaLaCuota(double resultResultante) {
                        errorFirst.postValue(null);
                        errorSecond.postValue(null);
                        result.postValue(resultResultante);
                    }

                    @Override
                    public void cuandoHayaErrorDeCapitalInferiorAlMinimo(double capitalMinimo) {
                        errorFirst.postValue(capitalMinimo);
                    }

                    @Override
                    public void cuandoHayaErrorDePlazoInferiorAlMinimo(double plazoMinimo) {
                        errorSecond.postValue(plazoMinimo);
                    }

                    @Override
                    public void cuandoEmpieceElCalculo() {
                        calculando.postValue(true);
                    }

                    @Override
                    public void cuandoFinaliceElCalculo() {
                        calculando.postValue(false);
                    }
                });
            }
        });
    }
        public void calcular_restar(double first, double second) {

            final SimuladorCalculadora.Solicitud solicitud = new SimuladorCalculadora.Solicitud(first, second);

            executor.execute(new Runnable() {
                @Override
                public void run() {

                    simulador.calcular_restar(solicitud, new SimuladorCalculadora.Callback() {
                        @Override
                        public void cuandoEsteCalculadaLaCuota(double resultResultante) {
                            errorFirst.postValue(null);
                            errorSecond.postValue(null);
                            result.postValue(resultResultante);
                        }

                        @Override
                        public void cuandoHayaErrorDeCapitalInferiorAlMinimo(double capitalMinimo) {
                            errorFirst.postValue(capitalMinimo);
                        }

                        @Override
                        public void cuandoHayaErrorDePlazoInferiorAlMinimo(double plazoMinimo) {
                            errorSecond.postValue(plazoMinimo);
                        }

                        @Override
                        public void cuandoEmpieceElCalculo() {
                            calculando.postValue(true);
                        }

                        @Override
                        public void cuandoFinaliceElCalculo() {
                            calculando.postValue(false);
                        }
                    });
                }
            });

        }

    public void calcular_multiplicar(double first, double second) {

        final SimuladorCalculadora.Solicitud solicitud = new SimuladorCalculadora.Solicitud(first, second);

        executor.execute(new Runnable() {
            @Override
            public void run() {

                simulador.calcular_multiplicar(solicitud, new SimuladorCalculadora.Callback() {
                    @Override
                    public void cuandoEsteCalculadaLaCuota(double resultResultante) {
                        errorFirst.postValue(null);
                        errorSecond.postValue(null);
                        result.postValue(resultResultante);
                    }

                    @Override
                    public void cuandoHayaErrorDeCapitalInferiorAlMinimo(double capitalMinimo) {
                        errorFirst.postValue(capitalMinimo);
                    }

                    @Override
                    public void cuandoHayaErrorDePlazoInferiorAlMinimo(double plazoMinimo) {
                        errorSecond.postValue(plazoMinimo);
                    }

                    @Override
                    public void cuandoEmpieceElCalculo() {
                        calculando.postValue(true);
                    }

                    @Override
                    public void cuandoFinaliceElCalculo() {
                        calculando.postValue(false);
                    }
                });
            }
        });
    }

    public void calcular_dividir(double first, double second) {

        final SimuladorCalculadora.Solicitud solicitud = new SimuladorCalculadora.Solicitud(first, second);

        executor.execute(new Runnable() {
            @Override
            public void run() {

                simulador.calcular_division(solicitud, new SimuladorCalculadora.Callback() {
                    @Override
                    public void cuandoEsteCalculadaLaCuota(double resultResultante) {
                        errorFirst.postValue(null);
                        errorSecond.postValue(null);
                        result.postValue(resultResultante);
                    }

                    @Override
                    public void cuandoHayaErrorDeCapitalInferiorAlMinimo(double capitalMinimo) {
                        errorFirst.postValue(capitalMinimo);
                    }

                    @Override
                    public void cuandoHayaErrorDePlazoInferiorAlMinimo(double plazoMinimo) {
                        errorSecond.postValue(plazoMinimo);
                    }

                    @Override
                    public void cuandoEmpieceElCalculo() {
                        calculando.postValue(true);
                    }

                    @Override
                    public void cuandoFinaliceElCalculo() {
                        calculando.postValue(false);
                    }
                });
            }
        });
    }
}