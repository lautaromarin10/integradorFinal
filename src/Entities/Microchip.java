/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author lautaromarin
 */
public class Microchip extends Base {
    private String codigo, fechaImplantacion, veterinaria, observaciones;
    
    //constructor vacio
    public Microchip(){
       super();
    }
    
    //constructor completo
    public Microchip(int id, String codigo, String fechaImplantacion, String veterinaria, String observaciones){
        super(id, false);
        this.codigo = codigo;
        this.fechaImplantacion = fechaImplantacion;
        this.veterinaria = veterinaria;
        this.observaciones = observaciones;
    }
    
    
    //setters y getters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFechaImplantacion() {
        return fechaImplantacion;
    }

    public void setFechaImplantacion(String fechaImplantacion) {
        this.fechaImplantacion = fechaImplantacion;
    }

    public String getVeterinaria() {
        return veterinaria;
    }

    public void setVeterinaria(String veterinaria) {
        this.veterinaria = veterinaria;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    //Override de toString
    @Override 
    public String toString(){
        return "Microchip con codigo: " + codigo +
               "\nFecha de Implentación: " + fechaImplantacion +
               "\nVeterinaria: " + veterinaria +
               "\nObservaciones: " + observaciones;
    }
    
    
}
