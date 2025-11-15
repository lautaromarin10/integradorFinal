
package Main;

import java.util.Scanner;


public class MenuHandle {
    
     private static Scanner scanner;
    
    public static int solicitarOpcionValida() {
    scanner = new Scanner(System.in);
    int opcion;

    while (true) {
        System.out.print("Seleccione una opción válida: ");
        String input = scanner.nextLine();

        // Valida que sea un numero
        try {
            opcion = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número.");
            continue; // vuelve a pedir
        }

        // Validar que este en el rango de opciones validas
        int min = MenuOptions.totalDeOpciones()[0];
        int max = MenuOptions.totalDeOpciones()[1];

        if (opcion < min || opcion > max) {
            System.out.println("La opción seleccionada no es válida.");
            continue;
        }

        // Retorna si es valida.
        return opcion;
    }
    }
    
    public static boolean decisionDeContinuarPrograma() {
    while (true) {
        System.out.println("¿Deseas continuar el programa?\n1 - Sí\n2 - No");

        String input = scanner.nextLine();

        // Validar que sea un número
        try {
            int decision = Integer.parseInt(input);

            if (decision == 1) {
                return true;  // sigue el programa
            } else if (decision == 2) {
                return false; // termina el programa
            } else {
                System.out.println("Opción inválida. Por favor ingresa 1 o 2.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un número. Inténtalo nuevamente.");
        }
    }
}
    
    public static void procesarOpcionSeleccionada(int opcionSeleccionada){
        
        System.out.println("Seleccionaste la opcion: " + opcionSeleccionada);
        
        switch(opcionSeleccionada){
            case 1:
                MenuHandle.insertarMascota();
            case 2:
                MenuHandle.actualizarMascota();
            case 3:
                MenuHandle.eliminarMascota();
            case 4:
                MenuHandle.obtenerMascotaPorID();
            case 5:
                MenuHandle.listarTodasLasMascotas();
            case 6:
                MenuHandle.insertarMascota();
            case 7:
                MenuHandle.eliminarMascota();
            case 8:
                MenuHandle.obtenerMascotaPorID();
            case 9:
                MenuHandle.listarTodasLasMascotas();
            default:
                break;
            
        }
        
    }

    //opcion 1
    public static void insertarMascota(){
    }
    
    //opcion 2
    public static void actualizarMascota(){
        
    }
    //opcion 3
    public static void eliminarMascota(){
        
    }
    
    //opcion 4
    public static void obtenerMascotaPorID(){
        
    }
    
    //opcion 5
    public static void listarTodasLasMascotas(){
    
    }
    
    //opcion 6
    public static void insertarMicrochip(){
        
    }
    
    //opcion 7
    public static void eliminarMicrochip(){
        
    }
    
    //opcion 8
    public static void obtenerMicrochipPorID(){
    }
    
    //opcion 9
    public static void listarTodosLosMicrochips(){
    }
    
}
