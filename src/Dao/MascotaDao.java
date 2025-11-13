/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Entities.Mascota;
import java.util.List;

public interface MascotaDao extends GenericDao<Mascota> {
    
    // Método de búsqueda específico requerido para Mascota
    
    /** Busca todas las mascotas cuyo nombre de dueño contenga el texto buscado. */
    List<Mascota> buscarPorDuenio(String duenio) throws Exception;
}
