
package Main;

import Entities.Mascota;
import Entities.Microchip;
import Services.MascotaService;
import Services.MicrochipService;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class MenuHandle {
    
    private static Scanner scanner;
    private static MascotaService mascotaService = new MascotaService();
    //LAUTY: revisar porque MascotaService se puede utilizar y MicrochipService no
    private static MicrochipService microchipService = new MicrochipService();
    
    
    public static int solicitarOpcionValida() {
    scanner = new Scanner(System.in);
    int opcion;

        while (true) {
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
    
    public static void procesarOpcionSeleccionada(int opcionSeleccionada) throws Exception{
        
        System.out.println("Seleccionaste la opcion: " + opcionSeleccionada);
        
        switch(opcionSeleccionada){
            case 1:
                MenuHandle.insertarMascota();
                break;
            case 2:
                MenuHandle.actualizarMascota();
                break;
            case 3:
                MenuHandle.eliminarMascota();
                break;
            case 4:
                MenuHandle.buscarMascotaPorId();
                break;
            case 5:
                MenuHandle.listarTodasLasMascotas();
                break;
            case 6:
                MenuHandle.insertarMascota();
                break;
            case 7:
                MenuHandle.eliminarMascota();
                break;
            case 8:
                MenuHandle.buscarMicrochipPorId();
                break;
            case 9:
                MenuHandle.listarTodasLasMascotas();
            default:
                break;
            
        }
        
    }
    
    //FUNCIONES DEL PROGRAMA.

    //opcion 1
    public static void insertarMascota(){
    }
    
    //opcion 2
    public static void actualizarMascota(){
        
    }
    
    //opcion 3
    public static void eliminarMascota() throws Exception{
        
        try{
            System.out.println("Eliminar mascota por ID");
            Long id = Helper.solicitarIDValido();
            
            mascotaService.eliminar(id);
            System.out.println("Mascota eliminada con exito");
            
        } catch (Exception error){
            System.out.println("Error: La mascota no fue encontrada");
        }
        
        
    }
    
    //opcion 4
    public static void buscarMascotaPorId() throws Exception{
        
      
       Mascota mascotaBuscada = new Mascota();
       
       try{
           Long id = Helper.solicitarIDValido();
           System.out.println(id);
           mascotaBuscada = mascotaService.getById(id);
           
           if(mascotaBuscada == null){
                System.out.println("No encontramos mascota con el ID: " + id);
                return;
           }
           
           System.out.println("Mascota encontrada");
           System.out.println(mascotaBuscada);
           
           
       } catch (Exception error){
           throw new Exception (error);
       }
        
    }
    
    //opcion 5
    public static void listarTodasLasMascotas() throws Exception{
        
        List<Mascota> listaMascota = new ArrayList<>();

        try{
           listaMascota = mascotaService.getAll();
           
           for(Mascota mascota : listaMascota){
               System.out.println(mascota);
               System.out.println("-----");
           }
        } catch (Exception error){
            throw new Exception(error);
        }
        
    }
    
    //opcion 6
    public static void insertarMicrochip(){
        
    }
    
    //opcion 7
    public static void eliminarMicrochip() throws Exception{
        
        try{
            System.out.println("Eliminar microchip por ID");
            Long id = Helper.solicitarIDValido();
            
            microchipService.eliminar(id);
            System.out.println("Microchip eliminado con exito");
            
        } catch (Exception error){
            System.out.println("Error: El microchip no fue encontrada");
        }
        
    }
    
    //opcion 8 - LEER COMENTARIO ARRIBA EN LA LINEA 17 - Si se soluciona esto esta funcion anda.
    public static void buscarMicrochipPorId() throws Exception{
        
        Microchip microchipBuscado = new Microchip();
       
       try{
           Long id = Helper.solicitarIDValido();
           System.out.println(id);
           microchipBuscado = microchipService.getById(id);
           
           if(microchipBuscado == null){
               System.out.println("No encontramos microchip con el ID: " + id);
               return;
           }
           
           System.out.println("Microchip encontrada");
           System.out.println(microchipBuscado);
           
           
       } catch (Exception error){
           throw new Exception (error);
       }
    }
    
    //opcion 9
    public static void listarTodosLosMicrochips() throws Exception{
        
        
        List<Microchip> listaMicrochip = new ArrayList<>();
        
        try{
            listaMicrochip = microchipService.getAll();
            
            for(Microchip microchip : listaMicrochip){
                System.out.println(microchip);
            }
            
        } catch (Exception error){
            throw new Exception(error);
        }
        
    }
    
}


    