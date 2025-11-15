/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;

public class Base implements Serializable {

    private Long id; 
    private boolean eliminado;

    // constructor completo
    public Base(Long id, boolean eliminado){ 
        this.id = id;
        this.eliminado = eliminado;
    }

    // constructor por defecto
    protected Base(){
        this.eliminado = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){ 
        return id;
    }

    public void setEliminado(boolean eliminado){
        this.eliminado = eliminado;
    }

    public boolean estaEliminado(){
        return eliminado;
    }
}
