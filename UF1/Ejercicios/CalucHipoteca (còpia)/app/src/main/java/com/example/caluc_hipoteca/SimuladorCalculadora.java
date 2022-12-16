package com.example.caluc_hipoteca;

public class SimuladorCalculadora {

    public static class Solicitud {
        public double first;
        public double second;

        public Solicitud(double first, double second) {
            this.first = first;
            this.second = second;
        }
    }

    interface Callback {
        void cuandoEsteCalculadaLaCuota(double result);
        void cuandoHayaErrorDeCapitalInferiorAlMinimo(double capitalMinimo);
        void cuandoHayaErrorDePlazoInferiorAlMinimo(double plazoMinimo);
        void cuandoEmpieceElCalculo();
        void cuandoFinaliceElCalculo();
    }



    public void calcular_suma(Solicitud solicitud, Callback callback) {
        double valMax = 0;

        callback.cuandoEmpieceElCalculo();
        try {
            Thread.sleep(2500);  // long run operation
            valMax = 100000;
        } catch (InterruptedException e) {}

        boolean error = false;
        if (solicitud.first > valMax) {
            callback.cuandoHayaErrorDeCapitalInferiorAlMinimo(valMax);
            error = true;
        }

        if (solicitud.second > valMax) {
            callback.cuandoHayaErrorDePlazoInferiorAlMinimo(valMax);
            error = true;
        }

        if(!error) {
            callback.cuandoEsteCalculadaLaCuota(solicitud.first + solicitud.second);
        }
        callback.cuandoFinaliceElCalculo();
    }

    public void calcular_restar(Solicitud solicitud, Callback callback) {
        double valMax = 0;

        callback.cuandoEmpieceElCalculo();
        try {
            Thread.sleep(2500);  // long run operation
            valMax = 100000;
        } catch (InterruptedException e) {}

        boolean error = false;
        if (solicitud.first > valMax) {
            callback.cuandoHayaErrorDeCapitalInferiorAlMinimo(valMax);
            error = true;
        }

        if (solicitud.second > valMax) {
            callback.cuandoHayaErrorDePlazoInferiorAlMinimo(valMax);
            error = true;
        }

        if(!error) {
            callback.cuandoEsteCalculadaLaCuota(solicitud.first - solicitud.second);
        }
        callback.cuandoFinaliceElCalculo();
    }

    public void calcular_multiplicar(Solicitud solicitud, Callback callback) {
        double valMax = 0;

        callback.cuandoEmpieceElCalculo();
        try {
            Thread.sleep(2500);  // long run operation
            valMax = 100000;
        } catch (InterruptedException e) {}

        boolean error = false;
        if (solicitud.first > valMax) {
            callback.cuandoHayaErrorDeCapitalInferiorAlMinimo(valMax);
            error = true;
        }

        if (solicitud.second > valMax) {
            callback.cuandoHayaErrorDePlazoInferiorAlMinimo(valMax);
            error = true;
        }

        if(!error) {
            callback.cuandoEsteCalculadaLaCuota(solicitud.first * solicitud.second);
        }
        callback.cuandoFinaliceElCalculo();
    }

    public void calcular_division(Solicitud solicitud, Callback callback) {
        double valMax = 0;

        callback.cuandoEmpieceElCalculo();
        try {
            Thread.sleep(2500);  // long run operation
            valMax = 100000;
        } catch (InterruptedException e) {}

        boolean error = false;
        if (solicitud.first > valMax) {
            callback.cuandoHayaErrorDeCapitalInferiorAlMinimo(valMax);
            error = true;
        }

        if (solicitud.second > valMax) {
            callback.cuandoHayaErrorDePlazoInferiorAlMinimo(valMax);
            error = true;
        }

        if(!error) {
            callback.cuandoEsteCalculadaLaCuota(solicitud.first / solicitud.second);
        }
        callback.cuandoFinaliceElCalculo();
    }
}