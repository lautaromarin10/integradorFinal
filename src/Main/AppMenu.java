package Main;
import Main.MenuOptions;

public class AppMenu {
    
    private static boolean ejecutandose = true;
    
    public static void close(){
        ejecutandose = false;
    }
    
    public static void run(){
        
        while(ejecutandose){
            //Listo las opciones al usuario
            MenuOptions.listarOpciones();
            //selecciono una opcion valida
            int opcionSolicitada = MenuHandle.solicitarOpcionValida();
            //Proceso la opcion que selecciono
            MenuHandle.procesarOpcionSeleccionada(opcionSolicitada);
            
            //pregunta si quiere terminar el programa
            boolean continuar = MenuHandle.decisionDeContinuarPrograma();
            
            if(!continuar){
                close();
            }
        }

        
    }

}
