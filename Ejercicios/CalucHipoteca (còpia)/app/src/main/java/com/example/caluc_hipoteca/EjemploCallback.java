package com.example.caluc_hipoteca;

class EjemploCallback {

    public void main(){

        int resultado = metodoConReturn(10000);
        System.out.println(resultado);


        metodoConCallback(10000, new Callback() {
            @Override
            public void alRetornar(int resultado) {
                System.out.println(resultado);
            }
        });
    }

    // return
    int metodoConReturn(int datos){
        int r = datos*datos;
        return r;
    }


    // callback
    interface Callback {
        void alRetornar(int resultado);
    }

    void metodoConCallback(int datos, Callback callback){
        int r = datos*datos;
        callback.alRetornar(datos*datos);
    }
}