package com.babel.ahorcado.service;

public interface ComprobarPalabraService {

    public void subirPalabrasAlRepository();

    public String getPalabraParaComprobarSeleccionada();

    public String getPalabraParaMostrarConGuiones();

    public boolean aciertoFallo(String palabraIntroducida);

    public String mostrarPalabraConAciertos();

    public int getIntentosRestantes();

    public void setIntentosRestantes(int intentosRestantes);

    public void vaciarArrayIndices();

    public void setLetrasTotalesIntroducidas(String letrasTotalesIntroducidas);

    public boolean letrasIntroducidas(String palabraIntroducida);
    public void vaciarStringletrasIntroducidas();
}
