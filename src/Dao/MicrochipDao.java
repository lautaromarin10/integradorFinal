/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Entities.Microchip;

public interface MicrochipDao extends GenericDao<Microchip> {
    
    // Métodos de búsqueda específicos requeridos para Microchip
    
    /** Busca un Microchip por su código (campo UNIQUE). */
    Microchip leerPorCodigo(String codigo) throws Exception;
    
    /** Busca un Microchip por el ID de la Mascota asociada (FK en tabla microchips). */
    Microchip leerPorIdMascota(long idMascota) throws Exception;
}