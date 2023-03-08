package com.babel.ahorcado.infra.console;

import com.babel.ahorcado.service.ComprobarPalabraService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Component
public class ConsoleReader {

    private final ComprobarPalabraService comprobarPalabraService;

    public ConsoleReader(ComprobarPalabraService comprobarPalabraService) {
        this.comprobarPalabraService = comprobarPalabraService;
    }

    @PostConstruct
    public void ini() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        String opcionMenu;
        do {
            System.out.println("\nMENU");
            System.out.println("1.- Juego Nuevo");
            System.out.println("2.- Salir");
            System.out.println("Introduce una opcion: ");
            opcionMenu = sc.next();
            sc.nextLine();
            try {
                opcion = Integer.parseInt(opcionMenu);
            } catch (Exception e) {
                System.out.println("Opcion no valida, ingresa un numero");
            }
            switch (opcion) {
                case 1:
                    int i = 0;
                    boolean resultado = false;
                    comprobarPalabraService.setIntentosRestantes(8);
                    comprobarPalabraService.subirPalabrasAlRepository();
                    comprobarPalabraService.vaciarArrayIndices();
                    comprobarPalabraService.getPalabraParaComprobarSeleccionada();
                    comprobarPalabraService.vaciarStringletrasIntroducidas();
                    String palabraGuiones = comprobarPalabraService.getPalabraParaMostrarConGuiones();
                    do {
                        String respuesta;
                        if (i == 0) {
                            System.out.println(palabraGuiones);
                            i++;
                        }
                        if (comprobarPalabraService.getIntentosRestantes() != 0) {
                            System.out.println("Intentos restantes: " + comprobarPalabraService.getIntentosRestantes());
                        }
                        System.out.println("Introduce la letra o palabra que cree que es: ");
                        respuesta = sc.nextLine().toUpperCase();
                        if (comprobarPalabraService.letrasIntroducidas(respuesta)) {
                            System.out.println("Error, ya has introducido esa letra, prueba con otra.");
                        } else {
                            resultado = comprobarPalabraService.aciertoFallo(respuesta);
                        }
                        if (!resultado) {
                            System.out.println(comprobarPalabraService.mostrarPalabraConAciertos());
                        }
                    } while (!resultado);
                    break;
                case 2:
                    System.out.println("Hasta pronto!!");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 2);
    }
}
