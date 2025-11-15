/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import Entities.Mascota;
import java.util.Scanner;
/**
 *
 * @author lautaromarin
 */
public class Helper {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static long solicitarIDValido(){
            
        String input;
        Long id;
        
        while(true){
            System.out.println("Ingrese un ID valido");
            input = scanner.nextLine();
            
            try{
                id = Long.parseLong(input);
            } catch (NumberFormatException e){
                System.out.println("Debe ingresar un numero");
                continue;
            }
            
           return id;
            
        }
    }
    
    
}
