/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import java.util.List;
/**
 *
 * @author Mati
 */

public interface GenericService<T, ID> { // T y ID son los genéricos
    void insertar(T entidad) throws Exception;
    void actualizar(T entidad) throws Exception;
    void eliminar(ID id) throws Exception;
    T getById(ID id) throws Exception;
    List<T> getAll() throws Exception;
}