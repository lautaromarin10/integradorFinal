package Main;

public class MenuOptions {
    
    public static int[] totalDeOpciones(){
        //el indice 0 es la primera opcion
        //el indice 9 es la ultima opcion
        return new int[]{1, 9};
    }
    
    
    public static void listarOpciones() {
        System.out.println("-- Opciones --");
        //Opciones de Animales
        System.out.println("1 - Insertar mascota");
        System.out.println("2 - Actualizar mascota existente");
        System.out.println("3 - Eliminar mascota");
        System.out.println("4 - Obtener mascota por ID");
        System.out.println("5 - Listar todas las mascotas");
        //Opciones de Microchip
        System.out.println("6 - Insertar microchip");
        System.out.println("7 - Eliminar microchip");
        System.out.println("8 - Obtener microchip por ID");
        System.out.println("9 - Listar todos los microchips");
    }
}
