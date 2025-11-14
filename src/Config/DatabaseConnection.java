/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lautaromarin
 */
public class DatabaseConnection {
    
    //URL de conexion JDBC
    private static String URL = System.getProperty("db.url", "jdbc:mysql://localhost:3306/mascotaMicrochip");
    //Usuario
    private static String USER = System.getProperty("db.user", "root");
    //Password
    private static String PASSWORD = System.getProperty("db.password", "rootroot");

    //Conexión
    //Arroja SQLException en caso de que algo este mal.
    
    public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    //Validación de configuración
    
    private static void validateConfiguration(){
        if(URL == null || URL.trim().isEmpty()){
            throw new IllegalStateException("La URL de la BD no está configurada");
        }
        
        if(USER == null || USER.trim().isEmpty()){
            throw new IllegalStateException("El usuario de la BD no esta configurado");
        }
        
        if(PASSWORD == null){
            throw new IllegalStateException("La contraseña de la BD no esta configurada");
        }
        
    }

}
