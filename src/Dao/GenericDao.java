/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// dao/GenericDao.java

package Dao;

import java.sql.Connection;
import java.util.List;

public interface GenericDao<T> {

    // 1. Métodos CRUD básicos (Gestionan su propia conexión - Uso simple)
    
    /** Crea un nuevo registro en la base de datos. */
    T crear(T entity) throws Exception; 

    /** Lee un registro por ID. */
    T leer(long id) throws Exception;

    /** Lee todos los registros NO eliminados. */
    List<T> leerTodos() throws Exception;

    /** Actualiza un registro existente. */
    void actualizar(T entity) throws Exception;

    /** Elimina LÓGICAMENTE un registro por ID. */
    void eliminar(long id) throws Exception; 
    
    // 2. Métodos CRUD sobrecargados (Para uso exclusivo en TRANSACCIONES del Service)
    
    /** Crea un registro usando una conexión externa (para transacciones). */
    T crear(T entity, Connection conn) throws Exception;

    /** Actualiza un registro usando una conexión externa (para transacciones). */
    void actualizar(T entity, Connection conn) throws Exception;
    
    /** Elimina LÓGICAMENTE un registro usando una conexión externa (para transacciones). */
    void eliminar(long id, Connection conn) throws Exception;
    
}