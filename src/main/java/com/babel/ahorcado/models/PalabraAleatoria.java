package com.babel.ahorcado.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PalabraAleatoria {

    private final List<String> palabras;

    public PalabraAleatoria() {
        palabras = new ArrayList<>();
        palabras.add("UNO");
        palabras.add("DOS");
        palabras.add("TRES");
        palabras.add("CUATRO");
        palabras.add("CINCO");
        palabras.add("SEIS");
        palabras.add("SIETE");
        palabras.add("OCHO");
        palabras.add("NUEVE");
        palabras.add("DIEZ");
        palabras.add("ONCE");
        palabras.add("DOCE");
        palabras.add("TRECE");
        palabras.add("CATORCE");
        palabras.add("QUINCE");
    }

    public List<String> getPalabras() {
        return palabras;
    }

    public String getPalabraSeleccionada(int num) {
        return palabras.get(num);
    }
}
