/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author lautaromarin
 */
public class Base {
    private long id;
    private boolean eliminado;
   
  
    //constructor completo
    public Base(long id, boolean eliminado){
        this.id = id;
        this.eliminado = eliminado;
    }
    
    //constructor por defecto
    protected Base(){
        this.eliminado = false;
    }
    
    
    public void setId(long id){
        this.id = id;
    }
    
    public long getId(){
        return id;
    }
    
    
    public void setEliminado(boolean eliminado){
        this.eliminado = eliminado;
    }
    
    public boolean estaEliminado(){
        return eliminado;
    }
    
    
}
