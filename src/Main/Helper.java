/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import java.time.DateTimeException;
import java.time.LocalDate;
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

    public static java.sql.Date solicitarFechaValida() {

    while (true) {
        try {
            System.out.println("Ingrese año (YYYY):");
            int anio = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese mes (1-12):");
            int mes = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese día (1-31):");
            int dia = Integer.parseInt(scanner.nextLine());

            // Validar creando LocalDate
            LocalDate fechaLocal = LocalDate.of(anio, mes, dia);

            // Convertir a java.sql.Date
            return java.sql.Date.valueOf(fechaLocal);

        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar números válidos.");
        } catch (DateTimeException e) {
            System.out.println("Fecha inválida. Intente nuevamente.");
        }
    }
}
    
    
}
