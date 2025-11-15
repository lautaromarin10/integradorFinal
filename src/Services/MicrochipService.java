/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Config.DatabaseConnection;
import Dao.MicrochipDao;
import Dao.MicrochipDaoJdbc;
import Entities.Microchip;
import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;

public class MicrochipService implements GenericService<Microchip, Long> {

    // Inicialización del DAO para acceder a la capa de persistencia
    private final MicrochipDao microchipDao = new MicrochipDaoJdbc();

    // -------------------------------------------------------------
    // --- INSERTAR (CREATE)
    // -------------------------------------------------------------

    @Override
    public void insertar(Microchip microchip) throws Exception {
        if (microchip == null) {
            throw new IllegalArgumentException("El microchip a insertar no puede ser nulo.");
        }
        
        // 1. Validación de unicidad: El código debe ser único.
        if (microchipDao.leerPorCodigo(microchip.getCodigo()) != null) {
            throw new Exception("Error: El código de microchip '" + microchip.getCodigo() + "' ya está registrado.");
        }

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // <--- INICIO DE TRANSACCIÓN

            // Crear el Microchip usando la conexión transaccional
            microchipDao.crear(microchip, conn);
            
            conn.commit(); // Confirmar si todo es exitoso

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Deshacer en caso de error SQL
            }
            throw new Exception("Error transaccional al insertar Microchip: " + e.getMessage(), e);
        } catch (Exception e) {
            if (conn != null && !conn.getAutoCommit()) {
                conn.rollback(); // Deshacer en caso de error de validación o DAO
            }
            throw e; // Propagar la excepción (ej: código duplicado)
        } finally {
            // Se realiza la lógica de cierre y restauración de la conexión
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeE) {
                    // Manejo de error al cerrar o restaurar
                    System.err.println("Error al cerrar la conexión: " + closeE.getMessage());
                }
            }
        }
    }

    // -------------------------------------------------------------
    // --- ACTUALIZAR (UPDATE)
    // -------------------------------------------------------------

    @Override
    public void actualizar(Microchip microchip) throws Exception {
        if (microchip == null || microchip.getId() == 0) {
            throw new IllegalArgumentException("Microchip inválido o sin ID para actualizar.");
        }
        
        // 1. Validación de unicidad: Verificar si el código ha cambiado y ya existe en otro registro.
        Microchip existentePorCodigo = microchipDao.leerPorCodigo(microchip.getCodigo());
        if (existentePorCodigo != null && existentePorCodigo.getId() != microchip.getId()) {
            throw new Exception("Error: El código de microchip '" + microchip.getCodigo() + "' ya pertenece a otro registro.");
        }

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // <--- INICIO DE TRANSACCIÓN

            // Actualizar el Microchip usando la conexión transaccional
            microchipDao.actualizar(microchip, conn);

            conn.commit(); // Confirmar si todo es exitoso

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Deshacer en caso de error SQL
            }
            throw new Exception("Error transaccional al actualizar Microchip: " + e.getMessage(), e);
        } catch (Exception e) {
            if (conn != null && !conn.getAutoCommit()) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeE) {
                    System.err.println("Error al cerrar la conexión: " + closeE.getMessage());
                }
            }
        }
    }

    // -------------------------------------------------------------
    // --- ELIMINAR (DELETE - Lógico)
    // -------------------------------------------------------------

    @Override
    public void eliminar(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido para eliminar.");
        }

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // <--- INICIO DE TRANSACCIÓN

            // Verificar si el microchip existe antes de intentar eliminar
            if (microchipDao.leer(id) == null) {
                 throw new Exception("Microchip con ID " + id + " no encontrado para eliminación.");
            }
            
            // ¡IMPORTANTE! Cuando eliminas un Microchip, la clave foránea con ON DELETE CASCADE 
            // en la tabla `mascota` DEBERÍA eliminar o poner a NULL la Mascota asociada.
            // Si la FK en DB es NOT NULL (como la definiste), eliminar el Microchip 
            // automáticamente **eliminará la Mascota asociada**. 
            // Si quieres evitar esto, debes quitar la restricción NOT NULL de la FK o
            // primero desasociar la mascota. Por ahora, nos basamos en tu DDL.

            // Eliminar lógicamente usando la conexión transaccional
            microchipDao.eliminar(id, conn);

            conn.commit(); // Confirmar si todo es exitoso

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Deshacer en caso de error SQL
            }
            throw new Exception("Error transaccional al eliminar Microchip: " + e.getMessage(), e);
        } catch (Exception e) {
            if (conn != null && !conn.getAutoCommit()) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeE) {
                    System.err.println("Error al cerrar la conexión: " + closeE.getMessage());
                }
            }
        }
    }

    // -------------------------------------------------------------
    // --- CONSULTAS (READ) - No necesitan transacción
    // -------------------------------------------------------------

    @Override
    public Microchip getById(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido para la consulta.");
        }
        try {
            return microchipDao.leer(id); 
        } catch (Exception e) {
            throw new Exception("Error al obtener Microchip por ID: " + id, e);
        }
    }

    @Override
    public List<Microchip> getAll() throws Exception {
        try {
            return microchipDao.leerTodos(); 
        } catch (Exception e) {
            throw new Exception("Error al obtener la lista de Microchips.", e);
        }
    }
}