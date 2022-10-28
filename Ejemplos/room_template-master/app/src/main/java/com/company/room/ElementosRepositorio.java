package com.company.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class ElementosRepositorio {


    ElementosBaseDeDatos.ElementosDao elementosDao;

    ElementosRepositorio(Application application){
        elementosDao = ElementosBaseDeDatos.obtenerInstancia(application).obtenerElementosDao();
    }


    interface Callback {
        void cuandoFinalice(List<Elemento> elementos);
    }

    ElementosRepositorio(){
    }

    List<Elemento> obtener() { }

    void insertar(Elemento elemento, Callback callback){

    }

    void eliminar(Elemento elemento, Callback callback) {

    }

    void actualizar(Elemento elemento, float valoracion, Callback callback) {

    }

    LiveData<List<Elemento>> obtener(){
        return elementosDao.obtener();
    }
}
