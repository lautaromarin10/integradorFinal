/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import java.time.LocalDate;

/**
 *
 * @author lautaromarin
 */
public class Mascota extends Base {
    private String nombre, especie, raza, duenio, telefonoDuenio;
    private Microchip microchip;
    private LocalDate fechaNacimiento;
    
    //constructor vacio
    public Mascota(){
        super();
    }
    
    
   //constructor completo
    public Mascota(long id, String nombre, String especie, String raza, LocalDate fechaNacimiento, String duenio, String telefonoDuenio, Microchip microchip){
        super(id, false);
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.duenio = duenio;
        this.telefonoDuenio = telefonoDuenio;
        this.microchip = microchip;
    }
    
    
    //setters y getters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public String getTelefonoDuenio() {
        return telefonoDuenio;
    }

    public void setTelefonoDuenio(String telefonoDuenio) {
        this.telefonoDuenio = telefonoDuenio;
    }

    public Microchip getMicrochip() {
        return microchip;
    }

    public void setMicrochip(Microchip microchip) {
        this.microchip = microchip;
    }
    
   
    
    
    //Override de toString
    @Override
    public String toString(){
        return "Mascota" +
               "\nNombre: " + nombre + 
                "\nEspecie: " + especie +
                "\nRaza: " + raza + 
                "\nFecha de Nacimiento: " + fechaNacimiento +
                "\nDueño: " + duenio +
                "\nTelefono del Dueño: " + telefonoDuenio;
    }
    
}