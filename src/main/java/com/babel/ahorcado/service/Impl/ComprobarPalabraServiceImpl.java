package com.babel.ahorcado.service.Impl;

import com.babel.ahorcado.models.PalabraAleatoria;
import com.babel.ahorcado.repository.PalabrasRepository;
import com.babel.ahorcado.service.ComprobarPalabraService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComprobarPalabraServiceImpl implements ComprobarPalabraService {
    private final PalabrasRepository palabrasRepositoryMemory;
    private int intentosRestantes;
    private final PalabraAleatoria palabraAleatoria;
    private String palabraSolucion;
    private String palabraParaMostrar;
    private String letrasTotalesIntroducidas;
    private final List<Integer> indiceLetrasParaMostrar;

    public ComprobarPalabraServiceImpl(PalabrasRepository palabrasRepositoryMemory, PalabraAleatoria palabraAleatoria) {
        this.palabrasRepositoryMemory = palabrasRepositoryMemory;
        this.palabraAleatoria = palabraAleatoria;
        this.intentosRestantes = 8;
        this.palabraParaMostrar = "";
        this.letrasTotalesIntroducidas = "";
        indiceLetrasParaMostrar = new ArrayList<>();
    }

    @Override
    public void subirPalabrasAlRepository() {
        palabrasRepositoryMemory.addPalabrasAleatorias(palabraAleatoria.getPalabras());
    }

    @Override
    public String getPalabraParaComprobarSeleccionada() {
        palabraSolucion = palabraAleatoria.getPalabraSeleccionada((int) (Math.random() * 9));
        return palabraSolucion;
    }

    @Override
    public String getPalabraParaMostrarConGuiones() {
        palabraParaMostrar = "";
        int numeroLetras = palabraSolucion.length();
        for (int i = 0; i < numeroLetras; i++) {
            palabraParaMostrar += "_";
        }
        return palabraParaMostrar;
    }

    @Override
    public boolean letrasIntroducidas(String palabraIntroducida) {
        boolean letraintroducida = letrasTotalesIntroducidas.contains(palabraIntroducida);
        if (palabraIntroducida.length() == 1) {
            letrasTotalesIntroducidas += palabraIntroducida;
        }
        return letraintroducida;
    }
    @Override
    public void vaciarStringletrasIntroducidas(){
        letrasTotalesIntroducidas = "";
    }

    @Override
    public void setLetrasTotalesIntroducidas(String letrasTotalesIntroducidas) {
        this.letrasTotalesIntroducidas = letrasTotalesIntroducidas;
    }

    @Override
    public boolean aciertoFallo(String palabraIntroducida) {
        boolean resultado = false;
        boolean letraEncontrada = false;
        if (intentosRestantes > 1) {
            if (palabraIntroducida.equals(palabraSolucion) && palabraIntroducida.length() > 1) {
                System.out.println("CORRECTO, la palabra es: " + palabraIntroducida + ", has ganado el juego introduciendo la palabra completa.");
                resultado = true;
            } else if (!palabraIntroducida.equals(palabraSolucion) && palabraIntroducida.length() > 1) {
                intentosRestantes--;
            } else {
                for (int i = 0; i < palabraSolucion.length(); i++) {
                    if (palabraSolucion.charAt(i) == palabraIntroducida.charAt(0)) {
                        letraEncontrada = true;
                        indiceLetrasParaMostrar.add(i);
                    }
                }
                if (!letraEncontrada) {
                    intentosRestantes--;
                }
            }
            if (mostrarPalabraConAciertos().equals(palabraSolucion)) {
                System.out.println("Felicidades has adivinado la palabra, la solucion era: " + palabraSolucion);
                resultado = true;
            }
        } else {
            System.out.println("Has llegado al limite de intentos, has perdido. La palabra era: " + palabraSolucion);
            resultado = true;
        }
        return resultado;
    }

    @Override
    public String mostrarPalabraConAciertos() {
        palabraParaMostrar = "";
        boolean encontrada;
        for (int i = 0; i < palabraSolucion.length(); i++) {
            encontrada = false;
            for (Integer integer : indiceLetrasParaMostrar) {
                if (i == integer) {
                    palabraParaMostrar += palabraSolucion.charAt(integer);
                    encontrada = true;
                }
            }
            if (!encontrada) {
                palabraParaMostrar += "_";
            }
        }
        return palabraParaMostrar;
    }

    @Override
    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    @Override
    public void setIntentosRestantes(int intentosRestantes) {
        this.intentosRestantes = intentosRestantes;
    }

    @Override
    public void vaciarArrayIndices() {
        indiceLetrasParaMostrar.clear();
    }
}
