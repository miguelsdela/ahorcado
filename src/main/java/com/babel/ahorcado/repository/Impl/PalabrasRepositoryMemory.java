package com.babel.ahorcado.repository.Impl;

import com.babel.ahorcado.repository.PalabrasRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PalabrasRepositoryMemory implements PalabrasRepository {

    private final List<String> copiaListPalabrasAleatorias;

    public PalabrasRepositoryMemory(List<String> copiaListPalabrasAleatorias) {
        this.copiaListPalabrasAleatorias = copiaListPalabrasAleatorias;
    }

    @Override
    public void addPalabrasAleatorias(List<String> palabrasAleatorias) {
        copiaListPalabrasAleatorias.addAll(palabrasAleatorias);
    }
}
